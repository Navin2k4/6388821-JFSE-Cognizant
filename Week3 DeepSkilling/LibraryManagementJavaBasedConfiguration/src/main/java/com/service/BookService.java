package com.service;

import com.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private String serviceName;
    private BookRepository bookRepository;

    @Autowired
    public BookService(@Value("LibraryBookService") String serviceName) {
        this.serviceName = serviceName;
        System.out.println("Constructor Injection: Service Name = " + serviceName);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        System.out.println("Setter Injection: BookRepository injected");
    }

    public void listBooks() {
        System.out.println("[" + serviceName + "] Listing books...");
    }
}
