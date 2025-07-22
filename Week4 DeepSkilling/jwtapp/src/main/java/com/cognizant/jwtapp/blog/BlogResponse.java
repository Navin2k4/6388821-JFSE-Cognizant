package com.cognizant.jwtapp.blog;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BlogResponse {
    private Integer id;
    private String title;
    private String content;
    private String authorEmail;
    private LocalDateTime createdAt;
}
