package com.backend.testManagement.mapper;

import com.backend.testManagement.dto.BlogDTO;
import com.backend.testManagement.dto.BlogDTOSave;
import com.backend.testManagement.model.Blog;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public BlogDTO mapToDTO(Blog blog) {
        return BlogDTO.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .description(blog.getDescription())
                .image(blog.getImage())
                .createdAt(blog.getCreatedAt())
                .author(blog.getAuthor())
                .category(blog.getCategory())
                .build();
    }

    public Blog mapToEntity(BlogDTOSave blogDTO) {
        return Blog.builder()
                .title(blogDTO.getTitle())
                .description(blogDTO.getDescription())
                .image(blogDTO.getImage())
                .author(blogDTO.getAuthor())
                .category(blogDTO.getCategory())
                .build();
    }

}