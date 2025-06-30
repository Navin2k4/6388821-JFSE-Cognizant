### Refer LibraryManagementJavaBasedConfigurtion

### Exercise 8: Implementing Basic AOP with Spring

Steps:

1. Define an Aspect:
   o Create a package com.library.aspect and add a class LoggingAspect.

2. Create Advice Methods:
   o Define advice methods in LoggingAspect for logging before and after method execution.

```JAVA
    package com.library.aspect;
    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.annotation.After;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.springframework.stereotype.Component;

    @Aspect
    @Component
    public class LoggingAspect {

        @Before("execution(* com.library.service.*.*(..))")
        public void beforeMethod(JoinPoint joinPoint) {
            System.out.println("[LOG BEFORE] Method: " + joinPoint.getSignature().getName() + " is about to be called");
        }

        @After("execution(* com.library.service.*.*(..))")
        public void afterMethod(JoinPoint joinPoint) {
            System.out.println("[LOG AFTER] Method: " + joinPoint.getSignature().getName() + " has completed");
        }
    }
```

3. Configure the Aspect:
   o Update applicationContext.xml to register the aspect and enable AspectJ auto-proxying.

   ```xml

   <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.9.24</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>6.2.8</version>
    </dependency>


   <beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xmlns:aop="http://www.springframework.org/schema/aop"
      xsi:schemaLocation="
          http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd
          http://www.springframework.org/schema/aop
          http://www.springframework.org/schema/aop/spring-aop.xsd">

   <context:component-scan base-package="com" />
   <aop:aspectj-autoproxy />
   </beans>
   ```

4. Test the Aspect:
   o Run the LibraryManagementApplication main class to verify the AOP functionality.

## O/P

Initializing Application context
Initialized Book Repo Constructor using the Context
Constructor Injection: Service Name = LibraryBookService
Setter Injection: BookRepository injected
