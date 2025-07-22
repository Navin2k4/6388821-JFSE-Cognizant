package com.cognizant.jwtapp.blog;

import lombok.Data;

@Data
public class CreateBlogRequest {
    private String title;
    private String content;
}
