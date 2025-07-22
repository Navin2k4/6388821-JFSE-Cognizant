package com.cognizant.jwtapp.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<BlogResponse>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }

    @PostMapping
    public ResponseEntity<BlogResponse> createBlog(@RequestBody CreateBlogRequest request) {
        BlogResponse response = blogService.createBlog(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user")
    public ResponseEntity<List<BlogResponse>> getMyBlogs(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(blogService.getUserBlogs(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BlogResponse> getBlogById(@PathVariable Integer id){
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(
            @PathVariable Integer id,
            @RequestBody CreateBlogRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return ResponseEntity.ok(blogService.updateBlog(id, request, userDetails.getUsername()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(
            @PathVariable Integer id,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        blogService.deleteBlog(id, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BlogResponse>> searchBlogs(@RequestParam String keyword) {
        return ResponseEntity.ok(blogService.searchBlogs(keyword));
    }

}
