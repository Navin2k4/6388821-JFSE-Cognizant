package com.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public BookRepository(){
        System.out.println("Initialized Book Repo Constructor using the Context");
    }
}
