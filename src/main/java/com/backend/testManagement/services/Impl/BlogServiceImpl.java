package com.backend.testManagement.services.Impl;

import com.backend.testManagement.dto.CommonResponseDTO;
import com.backend.testManagement.dto.BlogDTO;
import com.backend.testManagement.dto.BlogDTOSave;
import com.backend.testManagement.dto.ValidationUtilsDTO;
import com.backend.testManagement.exceptions.BadRequestException;
import com.backend.testManagement.exceptions.EntityNotFoundException;
import com.backend.testManagement.exceptions.InternalServerErrorException;
import com.backend.testManagement.mapper.BlogMapper;
import com.backend.testManagement.model.Blog;
import com.backend.testManagement.repository.BlogRepository;
import com.backend.testManagement.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {
    private static final Logger logger = Logger.getLogger(BlogServiceImpl.class.getName());

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, BlogMapper blogMapper) {
        this.blogRepository = blogRepository;
        this.blogMapper = blogMapper;
    }

    @Override
    @Transactional
    public BlogDTO saveBlog(BlogDTOSave blogDTO) {
        try {
            Blog blog = blogMapper.mapToEntity(blogDTO);
            Blog savedTest = blogRepository.save(blog);
            return blogMapper.mapToDTO(savedTest);
        } catch (DataAccessException ex) {
            logAndThrowInternalServerError("Error saving test", ex);
            return null;
        } catch (BadRequestException ex) {
            logAndThrowBadRequest("Invalid request: " + ex.getMessage());
            return null;
        }
    }


    @Override
    @Transactional
    public BlogDTO updateBlog(String id, BlogDTOSave blogDTO) {
        Blog existingBlog = findBlogById(id);
        existingBlog.setTitle(blogDTO.getTitle());
        existingBlog.setDescription(blogDTO.getDescription());
        existingBlog.setImage(blogDTO.getImage());
        existingBlog.setCategory(blogDTO.getCategory());
        existingBlog.setAuthor(blogDTO.getAuthor());
        Blog updatedBlog = blogRepository.save(existingBlog);
        logger.info("Blog updated successfully: " + id);
        return blogMapper.mapToDTO(updatedBlog);
    }


    @Override
    @Transactional
    public Blog deleteBlog(String id) {
        try {
            // Find the existing test by id
            Blog existingBlog = findBlogById(id);

            // Delete the test from the repository
            blogRepository.delete(existingBlog);

            // Log the successful deletion
            logger.info("Blog deleted successfully: " + id);
            return existingBlog;
        } catch (DataAccessException ex) {
            logAndThrowInternalServerError("Error deleting blog", ex);
        } catch (EntityNotFoundException ex) {
            logAndThrowEntityNotFoundException("Blog not found: " + id);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CommonResponseDTO<BlogDTO> getAllBlogs(int pageNo, int pageSize, String sortBy, String sortDirection) {
        ValidationUtilsDTO.validatePageParameters(pageNo, pageSize);
        Sort sort = Sort.by(sortBy);
        if ("desc".equalsIgnoreCase(sortDirection)) {
            sort = sort.descending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Blog> blogPage = blogRepository.findAll(pageable);
        if (blogPage.isEmpty()) {
            logAndThrowEntityNotFoundException("No Blog found");
        }
        List<BlogDTO> blogDTOs = blogPage.getContent().stream()
                .map(blogMapper::mapToDTO)
                .collect(Collectors.toList());
        return buildCommonResponse(blogDTOs, blogPage);
    }

    @Override
    public Blog findBlogById(String id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warning("Blog not found: " + id);
                    return new EntityNotFoundException("Blog not found");
                });
    }

    private void logAndThrowEntityNotFoundException(String message) {
        logger.warning(message);
        throw new EntityNotFoundException(message);
    }

    private void logAndThrowInternalServerError(String message, Exception ex) {
        logger.severe(message + ": " + ex.getMessage());
        throw new InternalServerErrorException(message);
    }

    private void logAndThrowBadRequest(String message) {
        logger.warning(message);
        throw new BadRequestException(message);
    }

    private CommonResponseDTO<BlogDTO> buildCommonResponse(List<BlogDTO> blogDTOs, Page<Blog> blogPage) {

        return CommonResponseDTO.<BlogDTO>builder()
                .list(blogDTOs)
                .totalItems(blogPage.getTotalElements())
                .currentPage(blogPage.getNumber())
                .pageNumber(blogPage.getNumber())
                .pageSize(blogPage.getSize())
                .build();

    }



}
