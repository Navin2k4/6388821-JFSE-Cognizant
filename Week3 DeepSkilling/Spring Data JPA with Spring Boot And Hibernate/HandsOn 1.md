## Structure
com.cognizant.orm_learn
┣ controller/
┃ ┗ CountryController.java
┣ service/
┃ ┗ CountryService.java
┣ repository/
┃ ┗ CountryRepository.java
┣ model/
┃ ┗ Country.java
┗ OrmLearnApplication.java

## Configurations application.peoperties

```properties
    spring.application.name=orm-learn
    server.port = 8090

    logging.level.org.springframework=info
    logging.level.com.cognizant=debug
    logging.level.org.hibernate.SQL=trace
    logging.level.org.hibernate.type.descriptor.sql=trace
    logging.pattern.console=%d{HH:mm:ss} [%level] %logger{20} - %msg%n

    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/ormlearn
    spring.datasource.username=root
    spring.datasource.password=navin
    spring.jpa.show-sql = true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

## Country Model Represents the Table 
```java
    @Entity
    @Table(name = "Country")
    @Data
    public class Country {
        @Id
        @Column(name = "co_code")
        private String code;
        @Column(name = "co_name")
        private String name;
        @Override
        public String toString() {
            return "Code : " + code + " Name : " + name;
        }
    }
```

## CountryRepository Repo Handle Database logic
```java
    public interface CountryRepository extends JpaRepository<Country, String> {
    }
```

## CountryService Service to handle Business Logic
```java
    @Service
    public class CountryService {

        @Autowired
        private CountryRepository countryRepository;

        public List<Country> getAllCountries() {
            return countryRepository.findAll();
        }

        public Country getCountry(String code) {
            return countryRepository.findById(code).orElse(null);
        }

        public Country addCountry(Country country) {
            return countryRepository.save(country);
        }

        public void deleteCountry(String code) {
            countryRepository.deleteById(code);
        }
    }
```
## CountryController Controller to handle REST API Calls
```java
    @RestController
    @RequestMapping("/countries")
    public class CountryController {

        @Autowired
        private CountryService countryService;

        @GetMapping
        public List<Country> getAllCountries() {
            return countryService.getAllCountries();
        }

        @GetMapping("/{code}")
        public Country getCountry(@PathVariable String code) {
            return countryService.getCountry(code);
        }

        @PostMapping
        public Country addCountry(@RequestBody Country country) {
            return countryService.addCountry(country);
        }

        @DeleteMapping("/{code}")
        public void deleteCountry(@PathVariable String code) {
            countryService.deleteCountry(code);
        }
    }
```

### ORM

ORM (Object-Relational Mapping) is a programming technique that allows developers to interact with a relational database (like MySQL, PostgreSQL) using object-oriented programming (like Java, Python, etc.), instead of writing raw SQL queries.

ORM maps Java objects (Entities) to database tables and their relationships.

@Entity
public class Country {
    @Id
    private String code;
    private String name;
}

To

CREATE TABLE Country (
    code VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255)
);

## In Java, ORM is implemented via:
JPA (Java Persistence API): Specification (interface/standard)
Hibernate: Popular ORM implementation of JPA (actual library)

## How ORM Works (Spring Boot Flow)
User Request
   ↓
Controller
   ↓
Service
   ↓
Repository (extends JpaRepository)
   ↓
Hibernate ORM
   ↓
Database (MySQL/Postgres)

## ORM handles:
SQL generation
Query execution
Result mapping (ResultSet → Java object)
Relationship mapping (OneToMany, ManyToOne)

## Benifits

Automatic Mapping   	    Converts between Java objects and SQL tables seamlessly
Cleaner Code	            Reduces boilerplate JDBC code
Rapid Development	        Speeds up development with save(), findAll(), etc.
Security	                Helps prevent SQL injection with parameterized queries
Portable	                Switch DBs (e.g., MySQL → PostgreSQL) with minimal changes
Declarative Relationships	Easily map foreign keys via annotations
Transaction Management	    Built-in support for ACID-compliant transactions

## Disadvantages

Performance Overhead	    ORM generates more generalized SQL; not always optimized
Learning Curve          	Understanding lifecycle, lazy loading, cascade, etc. takes time
Black Box Behavior	        You may not always know what SQL is being executed unless logging
Less Control	            Fine-tuning performance (e.g., joins, bulk updates) is harder
Impedance Mismatch	        Some DB concepts (like triggers, stored procedures) don’t map well to objects
Debugging is Complex	    Lazy loading, proxy issues, N+1 query problems can be hard to trace


## Use ORM when:
- You want rapid CRUD development
- You're building typical business apps (admin dashboards, e-commerce, HR systems)
- You want to focus more on Java logic than SQL

## Avoid ORM (or use it cautiously) when:
- You need high-performance, low-latency SQL
- You're dealing with complex reporting, analytics, or stored procedures
- Your database is heavily optimized manually

## Hibernate
Hibernate is an open-source, Object-Relational Mapping (ORM) library for Java.
It maps Java classes to relational database tables and provides a powerful abstraction layer for data persistence, allowing you to interact with your database using Java objects instead of SQL queries.

Hibernate is an implementation of the JPA (Java Persistence API) specification.

# Without Hibernate:

You need to write boilerplate JDBC code
Manually handle Connection, ResultSet, and error handling
Convert SQL result rows to Java objects

# With Hibernate:
You define a Java class with annotations like @Entity, and Hibernate maps it to the DB table.
You perform database operations using Session or Spring Data JPA.

Java Class ⇄ Hibernate ⇄ SQL Query ⇄ Database
Java objects (Entities) are mapped to tables
Hibernate uses Configuration to connect to the database
Internally, Hibernate:
- Generates SQL
- Manages transactions
- Handles caching, lazy loading, etc.
You interact with it using:
- Hibernate API (older)
- Or JPA with Spring Boot (modern)

## Advantages of Hibernate

ORM         	Maps objects to tables automatically
Productivity	Minimal SQL and boilerplate code
Transactions	Built-in support for transaction management
Caching	        First-level (default) and optional second-level caching
HQL         	Hibernate Query Language for object-based querying
Smart	        Handles connection pooling, lazy loading, relationships, etc.

## Disadvantages of Hibernate

Overhead        	Might generate inefficient SQL
Complexity	        Understanding lazy loading, sessions, proxies can be tough
Less SQL Control	Hard to fine-tune for performance-heavy queries
Learning Curve	    Requires deep understanding of ORM lifecycle and internals

Use Hibernate if:
- You need rapid development for DB-driven apps
- You prefer OOP-style access over SQL
- You want easy integration with Spring
Avoid if:
- You need 100% control over SQL performance tuning
- You heavily use stored procedures or native SQL logic