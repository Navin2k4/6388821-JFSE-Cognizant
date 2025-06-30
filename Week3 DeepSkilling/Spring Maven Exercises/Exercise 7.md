### Refer LibraryManagementJavaBasedConfigurtion

### Exercise 7: Implementing Constructor and Setter Injection

1. Configure Constructor Injection:
   o Update applicationContext.xml to configure constructor injection for BookService.

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation="
          http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd">

   <!-- Compontent Scaning -->
   <context:component-scan base-package="com"/>

   </beans>
   ```

2. Configure Setter Injection:
   o Ensure that the BookService class has a setter method for BookRepository and configure it in applicationContext.xml.

   ```java
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
   ```

3. Test the Injection:
   o Run the LibraryManagementApplication main class to verify both constructor and setter injection.

   ```java
   package com.library;

   import com.service.BookService;
   import org.springframework.context.ApplicationContext;
   import org.springframework.context.support.ClassPathXmlApplicationContext;

   public class Main {
       public static void main(String[] args) {
           System.out.println("Initializing Application context");
           ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
           BookService bookService = context.getBean(BookService.class);
       }
   }
   ```

## O/P

Initializing Application context
Initialized Book Repo Constructor using the Context
Constructor Injection: Service Name = LibraryBookService
Setter Injection: BookRepository injected
