package com.backend.testManagement.services;

import com.backend.testManagement.dto.*;
import com.backend.testManagement.model.Blog;
import com.backend.testManagement.model.Test;

public interface BlogService {
    CommonResponseDTO<BlogDTO> getAllBlogs(int pageNo, int pageSize, String sortBy, String sortDirection);
    BlogDTO saveBlog(BlogDTOSave blogDTO);
    Blog findBlogById(String id);
    Blog deleteBlog(String id);
    BlogDTO updateBlog(String id, BlogDTOSave blogDTO);

}
