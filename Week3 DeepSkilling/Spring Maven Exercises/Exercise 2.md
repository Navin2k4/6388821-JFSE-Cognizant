### Exercise 2: Implementing Dependency Injection

### Refer the Library Management Project Folder

1. Modify the XML Configuration:
   o Update applicationContext.xml to wire BookRepository into BookService.

```xml
    <bean id="bookService" class="com.service.BookService">
        <!-- Setter Injection -->
        <property name="bookRepository" ref="bookRepository"/>
    </bean>
    <bean id="bookRepository" class="com.repository.BookRepository"/>
```

2. Update the BookService Class:
   o Ensure that BookService class has a setter method for BookRepository.

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

3. Test the Configuration:
   o Run the LibraryManagementApplication main class to verify the dependency injection.

```java
public static void main(String[] args) {
        System.out.println("Initializing Application context");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = (BookService) context.getBean("bookService");
    }
```

# O/P

Initializing Application context
BookService Constructor Invoked
Initialized Book Repo Constructor using the Context
Setter Injection: BookRepository set in BookService

<!-- What the use of the Setter injection  -->

Why use setter-based dependency injection (DI) instead of just defining/initializing the object directly?

- Spring injects the dependency at runtime
- You can replace BookRepository with a mock, test double, or different implementation without changing BookService
- More maintainable, reusable, and testable

- Setter Injection is about delegating object responsibility to the Spring IoC container.
  Instead of saying “I know what to use,” you're saying “Hey Spring, plug in whatever is appropriate.”
- This is the foundation of Inversion of Control (IoC) and the Dependency Inversion Principle (SOLID).
