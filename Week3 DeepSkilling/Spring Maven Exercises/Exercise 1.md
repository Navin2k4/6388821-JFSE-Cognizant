### Exercise 1: Configuring a Basic Spring Application

### Refer the Library Management Project Folder

1.  Set Up a Spring Project:
    o Create a Maven project named LibraryManagement.
    o Add Spring Core dependencies in the pom.xml file.

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

2.  Configure the Application Context:
    o Create an XML configuration file named applicationContext.xml in the src/main/resources directory.
    o Define beans for BookService and BookRepository in the XML file.

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

3.  Define Service and Repository Classes:
    o Create a package com.library.service and add a class BookService.

    ```java
    package com.service;
    public class BookService {
    public BookService() {
    System.out.println("Initialized Book Service Constructor using the Context");
    }
    }
    ```

    o Create a package com.library.repository and add a class BookRepository.

    ```java
    package com.repository;
    public class BookRepository {
    public BookRepository(){
    System.out.println("Initialized Book Repo Constructor using the Context");
    }
    }
    ```

4.  Run the Application:
    o Create a main class to load the Spring context and test the configuration.

```java
    package com.library;
    import com.repository.BookRepository;
    import com.service.BookService;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.support.ClassPathXmlApplicationContext;

    public class Main {
    public static void main(String[] args) {
    System.out.println("Initializing Application context");
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookService bookService = (BookService) context.getBean("bookService");
    BookRepository bookRepository = (BookRepository) context.getBean("bookRepository");
    }
    }
```

# O/P

Initializing Application context
Initialized Book Repo Constructor using the Context
Initialized Book Service Constructor using the Context
