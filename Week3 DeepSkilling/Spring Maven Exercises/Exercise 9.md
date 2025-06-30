Exercise 9: Creating a Spring Boot Application

1. Create a Spring Boot Project:
   o Use Spring Initializr to create a new Spring Boot project named LibraryManagement.

2. Add Dependencies:
   o Include dependencies for Spring Web, Spring Data JPA, and H2 Database.

   ```xml
   	<dependencies>
   	<dependency>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-data-jpa</artifactId>
   	</dependency>
       <!-- <dependency>
           <groupId>com.mysql</groupId>
           <artifactId>mysql-connector-j</artifactId>
           <scope>runtime</scope>
       </dependency> -->
   	<dependency>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-web</artifactId>
   	</dependency>

   	<dependency>
   		<groupId>com.h2database</groupId>
   		<artifactId>h2</artifactId>
   		<scope>runtime</scope>
   	</dependency>
   	<dependency>
   		<groupId>org.projectlombok</groupId>
   		<artifactId>lombok</artifactId>
   		<optional>true</optional>
   	</dependency>
   	<dependency>
   		<groupId>org.springframework.boot</groupId>
   		<artifactId>spring-boot-starter-test</artifactId>
   		<scope>test</scope>
   	</dependency>
   </dependencies>

   <build>
   	<plugins>
   		<plugin>
   			<groupId>org.apache.maven.plugins</groupId>
   			<artifactId>maven-compiler-plugin</artifactId>
   			<configuration>
   				<annotationProcessorPaths>
   					<path>
   						<groupId>org.projectlombok</groupId>
   						<artifactId>lombok</artifactId>
   					</path>
   				</annotationProcessorPaths>
   			</configuration>
   		</plugin>
   		<plugin>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-maven-plugin</artifactId>
   			<configuration>
   				<excludes>
   					<exclude>
   						<groupId>org.projectlombok</groupId>
   						<artifactId>lombok</artifactId>
   					</exclude>
   				</excludes>
   			</configuration>
   		</plugin>
   	</plugins>
   </build>
   ```

3. Create Application Properties:
   o Configure database connection properties in application.properties.

    spring.application.name=LibraryManagementUsingSpringInitializer
    spring.datasource.url=jdbc:h2:mem:librarydb
    <!-- If needed to save the data -->
    <!-- spring.datasource.url=jdbc:h2:file:./data/librarydb -->
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.h2.console.enabled=true
    spring.jpa.show-sql=true
    spring.jpa.hibernate.ddl-auto=update

    <!-- http://localhost:8080/h2-console -->

4. Define Entities and Repositories:
   o Create Book entity and BookRepository interface.

   ```java
    package com.library.entity;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.Data;
    @Entity
    @Data
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String title;
        private String author;
        public Book() {}
        public Book(String title, String author) {
            this.title = title;
            this.author = author;
        }
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
    }

   ```

   ```java
    package com.library.repository;
    import com.library.entity.Book;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    public interface BookRepository extends JpaRepository<Book, Long> {}
   ```

5. Create a REST Controller:
   o Create a BookController class to handle CRUD operations.

   ```java
   package com.library.controller;
   import com.library.entity.Book;
   import com.library.repository.BookRepository;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Controller;
   import org.springframework.web.bind.annotation.*;
   import java.util.*;

   @Controller
   @RequestMapping("/api/books")
   public class BookController {

       @Autowired
       BookRepository bookRepository;

       @GetMapping
       public List<Book> getAllBooks() {
           return bookRepository.findAll();
       }

       @PostMapping
       public Book createBook(@RequestBody Book book) {
           return bookRepository.save(book);
       }

       @GetMapping("{/id}")
       public Book getBookById(@PathVariable Long id) {
           return bookRepository.findById(id).orElse(null);
       }

       @PutMapping("{/id}")
       public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
           Book book = bookRepository.findById(id).orElse(null);
           if (book != null) {
               book.setTitle(updatedBook.getTitle());
               book.setAuthor(updatedBook.getAuthor());
               return bookRepository.save(book);
           }
           return null;
       }

       @DeleteMapping("/{id}")
       public void deleteBook(@PathVariable Long id) {
           bookRepository.deleteById(id);
       }
   }


   ```

6. Run the Application:
   o Run the Spring Boot application and test the REST endpoints.


    .   ____          _            __ _ _
    /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
    \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
    '  |____| .__|_| |_|_| |_\__, | / / / /
    =========|_|==============|___/=/_/_/_/

    :: Spring Boot ::                (v3.5.3)
    2025-06-30T22:37:42.328+05:30  INFO 19356 --- [LibraryManagementUsingSpringInitializer] [           main] org.hibernate.orm.connections.pooling    : HHH10001005: Database info:
        Database JDBC URL [Connecting through datasource 'HikariDataSource (HikariPool-1)']
        Database driver: undefined/unknown
        Database version: 2.3.232
        Autocommit mode: undefined/unknown
        Isolation level: undefined/unknown
        Minimum pool size: undefined/unknown
        Maximum pool size: undefined/unknown


## O/P
[
  {
    "id": 1,
    "title": "Spring Maven Learn",
    "author": "Navin"
  },
  {
    "id": 2,
    "title": "Spring Maven Test",
    "author": "Vinoth"
  },
  {
    "id": 3,
    "title": "Spring Boot Utility",
    "author": "BAlaji"
  }
]

Hibernate: select b1_0.id,b1_0.author,b1_0.title from book b1_0
Hibernate: select b1_0.id,b1_0.author,b1_0.title from book b1_0