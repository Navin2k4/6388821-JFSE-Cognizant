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

    public void listBook() {
        System.out.println("Running ListBook in Service Class (Simulating a DB Fetch))");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
