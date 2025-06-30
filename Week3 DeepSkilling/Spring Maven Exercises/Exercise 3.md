### Refer the Library Management Project Folder

### Exercise 3: Implementing Logging with Spring AOP

1. Add Spring AOP Dependency:
   o Update pom.xml to include Spring AOP dependency.

   ```xml
   <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>6.2.8</version>
    </dependency>
     <dependency>
         <groupId>org.aspectj</groupId>
         <artifactId>aspectjweaver</artifactId>
         <version>1.9.24</version>
   </dependency>

   ```

2. Create an Aspect for Logging:
   o Create a package com.library.aspect and add a class LoggingAspect with a method to log execution times.

   ```java

   package com.library.aspect;

    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;

    @Aspect
    public class LoggingAspect {
    @Around("execution(_ com.service._.\*(..))")
        public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("[LOG] Method: " + joinPoint.getSignature().getName() + " executed in " + (endTime - startTime) + "ms");
        return result;
        }
    }
   ```

3. Enable AspectJ Support:
   o Update applicationContext.xml to enable AspectJ support and register the aspect.

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd"
>
     <aop:aspectj-autoproxy/>
     <bean id="loggingAspect" class="com.library.aspect.LoggingAspect"/>

```

4. Test the Aspect:
   o Run the LibraryManagementApplication main class and observe the console for log messages indicating method execution times.

   ```java
    public void listBook() {
        System.out.println("Running ListBook in Service Class (Simulating a DB Fetch))");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    BookService bookService = (BookService) context.getBean("bookService");
    bookService.listBook();
   ```

# O/P

Initializing Application context
BookService Constructor Invoked
Initialized Book Repo Constructor using the Context
Setter Injection: BookRepository set in BookService
Running ListBook in Service Class (Simulating a DB Fetch))
[LOG] Method: listBook executed in 107ms

@Around("execution(_ com.service._.\*(..))")
execution(...): Match method executions
*: Any return type
com.service.*: Any class inside com.service package
.*(..): Any method name, any number of arguments

<aop:aspectj-autoproxy/>

Scan for any @Aspect classes and automatically create proxies for beans that are matched by pointcut expressions.

A proxy is essentially a wrapper object that surrounds your original bean.
Spring creates this proxy to intercept method calls and apply cross-cutting logic like logging, security, etc.
Instead of calling your real bean directly, Spring gives you a proxy object that behaves the same, but has extra behavior injected.

Imagine you ask your assistant (Spring) to call a person (your bean):
    Instead of giving you the direct phone number of the person (bean),
    The assistant gives you their own number (proxy).
    When you call the assistant:
    They log your call time
    Then connect you to the person
So, you talk to the proxy, not the actual object. But you get the same response with extra behavior added.

Internally, it enables:
- BeanPostProcessor (AnnotationAwareAspectJAutoProxyCreator)
- Spring scans your @Aspect classes
- Matches pointcuts to your beans
- Creates proxy classes for those beans
