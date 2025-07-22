package com.cognizant.jwtapp.blog;

import com.cognizant.jwtapp.exception.ResourceNotFoundException;
import com.cognizant.jwtapp.exception.UnauthorizedActionException;
import com.cognizant.jwtapp.user.User;
import com.cognizant.jwtapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogService {

    private BlogResponse mapToResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .title(blog.getTitle())
                .content(blog.getContent())
                .authorEmail(blog.getAuthor().getEmail())
                .createdAt(blog.getCreatedAt())
                .build();
    }

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public BlogResponse createBlog(CreateBlogRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authentication Details : " + authentication);
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Blog blog = Blog.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .author(user)
                .build();

        blogRepository.save(blog);

        return mapToResponse(blog);
    }

    public List<BlogResponse> getAllBlogs() {
        return blogRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<BlogResponse> getUserBlogs(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return blogRepository.findByAuthorId(user.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public BlogResponse getBlogById(Integer id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

        return mapToResponse(blog);
    }

    public BlogResponse updateBlog(Integer id, CreateBlogRequest request, String userEmail) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException("Blog not found with id: " + id));
        if(!blog.getAuthor().getEmail().equals(userEmail)){
            throw new UnauthorizedActionException("You are not authorized to update this blog");
        }
        blog.setTitle(request.getTitle());
        blog.setContent(request.getContent());
        blog.setCreatedAt(LocalDateTime.now());
        Blog updatedBlog = blogRepository.save(blog);
        return mapToResponse(updatedBlog);
    }

    public void deleteBlog(Integer id, String username) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Blog not found with id: " + id));

        if (!blog.getAuthor().getEmail().equals(username)) {
            throw new UnauthorizedActionException("You are not authorized to delete this blog");
        }

        blogRepository.delete(blog);
    }

    public List<BlogResponse> searchBlogs(String keyword) {
        List<Blog> blogs = blogRepository.searchByKeyword(keyword.toLowerCase());
        return blogs.stream()
                .map(this::mapToResponse)
                .toList();
    }

}


