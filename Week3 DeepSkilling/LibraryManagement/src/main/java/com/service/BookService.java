package com.service;

import com.repository.BookRepository;

public class BookService {

    BookRepository bookRepository;

    public BookService() {
        System.out.println("BookService Constructor Invoked");
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("Setter Injection: BookRepository set in BookService");
    }
}
