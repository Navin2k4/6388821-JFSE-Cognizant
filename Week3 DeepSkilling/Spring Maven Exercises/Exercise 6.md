### Refer LibraryManagementJavaBasedConfigurtion

### Exercise 6: Configuring Beans with Annotations

1.  Enable Component Scanning:
    o Update applicationContext.xml to include component scanning for the com.library package.

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

2.  Annotate Classes:
    o Use @Service annotation for the BookService class.
    o Use @Repository annotation for the BookRepository class.

    ```java
    @Service
    public class BookService {}

    @Repository
    public class BookRepository {}
    ```

3.  Test the Configuration:
    o Run the LibraryManagementApplication main class to verify the annotation-based configuration.

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
Initialized Book Service Constructor using the Context