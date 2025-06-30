### Refer LibraryManagementJavaBasedConfigurtion

### Exercise 4: Creating and Configuring a Maven Project

1. Create a New Maven Project:
   o Create a new Maven project named LibraryManagement.
   Created

2. Add Spring Dependencies in pom.xml:
   o Include dependencies for Spring Context, Spring AOP, and Spring WebMVC.

   ```xml
   <dependencies>
        <dependency>
            <groupId>org.apache.maven</groupId>
            <artifactId>maven-core</artifactId>
            <version>3.2.5</version>
        </dependency>
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
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>6.2.7</version>
        </dependency>
    </dependencies>
   ```

3. Configure Maven Plugins:
   o Configure the Maven Compiler Plugin for Java version 1.8 in the pom.xml file.

   ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.14.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
   ```
