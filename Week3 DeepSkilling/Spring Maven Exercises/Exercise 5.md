### Refer LibraryManagementJavaBasedConfigurtion

### Exercise 5: Configuring the Spring IoC Container

1.  Create Spring Configuration File:
    o Create an XML configuration file named applicationContext.xml in the src/main/resources directory.
    o Define beans for BookService and BookRepository in the XML file.

    ```xml
     <dependencies>
         <dependency>
             <groupId>org.springframework</groupId>
             <artifactId>spring-core</artifactId>
             <version>6.2.8</version>
         </dependency>
         <dependency>
             <groupId>org.springframework</groupId>
             <artifactId>spring-context</artifactId>
             <version>6.2.8</version>
         </dependency>
     </dependencies>
    ```

    aplicationContext.xml file

        ```xml
        <?xml version="1.0" encoding="UTF-8"?>

        <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="
                http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
        <!-- bean definitions here -->
        <bean id="bookRepository" class="com.repository.BookRepository" />
        <bean id="bookService" class="com.service.BookService" />
        </beans>
        ```

2.  Update the BookService Class:
    o Ensure that the BookService class has a setter method for BookRepository.

    ```java
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
    ```

3.  Run the Application:
    o Create a main class to load the Spring context and test the configuration.

```java
package com.library;

import com.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing Application context");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");
    }
}
```

## O/P

Initializing Application context
BookService Constructor Invoked
Initialized Book Repo Constructor using the Context
Setter Injection: BookRepository set in BookService
