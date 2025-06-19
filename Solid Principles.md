# SOLID Principles

The SOLID principles are five design principles that make software designs more understandable, flexible, and maintainable.

## 1. Single Responsibility Principle (SRP)

**Definition:** A class should have only one reason to change. Every class should do only one job. This makes classes easier to test, modify, and debug.

###  Violating SRP

```java
class Invoice {
    public void calculateTotal() {
        // logic to calculate total
    }

    public void printInvoice() {
        // logic to print invoice
    }

    public void saveToDatabase() {
        // logic to save to DB
    }
}
```

###  Applying SRP

```java
class Invoice {
    public double calculateTotal() {
        // business logic
        return 1000.0;
    }
}

class InvoicePrinter {
    public void print(Invoice invoice) {
        // print logic
    }
}

class InvoiceRepository {
    public void save(Invoice invoice) {
        // database logic
    }
}
```

## 2. Open/Closed Principle (OCP)

**Definition:** Software entities should be open for extension, but closed for modification. We should be able to add new functionality without changing existing code.

###  Violating OCP

```java
class NotificationService {
    public void send(String message, String type) {
        if (type.equals("EMAIL")) {
            // send email
        } else if (type.equals("SMS")) {
            // send SMS
        }
    }
}
```

###  Applying OCP

Now we can extend by adding new notification types without modifying the service.

```java
interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        // send email
    }
}

class SMSNotification implements Notification {
    public void send(String message) {
        // send SMS
    }
}

class NotificationService {
    public void send(Notification notification, String message) {
        notification.send(message);
    }
}
```

## 3. Liskov Substitution Principle (LSP)

**Definition:** Subtypes must be substitutable for their base types. If S is a subtype of T, then we should be able to replace T with S without breaking the program.

###  Violating LSP

```java
class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

class Ostrich extends Bird {
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly!");
    }
}
```

###  Applying LSP

```java
interface Bird {}

interface FlyingBird extends Bird {
    void fly();
}

class Sparrow implements FlyingBird {
    public void fly() {
        System.out.println("Sparrow flying");
    }
}

class Ostrich implements Bird {
    // No fly method needed
}
```

## 4. Interface Segregation Principle (ISP)

**Definition:** Clients should not be forced to depend on methods they do not use. Break big interfaces into smaller, more specific ones.

###  Violating ISP

```java
interface Worker {
    void work();
    void eat();
}

class Robot implements Worker {
    public void work() {
        // robot working
    }

    public void eat() {
        // Not applicable
        throw new UnsupportedOperationException("Robots don't eat!");
    }
}
```

###  Applying ISP

```java
interface Workable {
    void work();
}

interface Eatable {
    void eat();
}

class Human implements Workable, Eatable {
    public void work() {
        // working
    }
    
    public void eat() {
        // eating
    }
}

class Robot implements Workable {
    public void work() {
        // working
    }
}
```

## 5. Dependency Inversion Principle (DIP)

**Definition:** High-level modules should not depend on low-level modules. Both should depend on abstractions. Depend on interfaces or abstract classes, not concrete implementations.

###  Violating DIP

```java
class MySQLDatabase {
    public void connect() {
        // connect logic
    }
}

class DataService {
    private MySQLDatabase db = new MySQLDatabase();

    public void fetchData() {
        db.connect();
    }
}
```

###  Applying DIP

```java
interface Database {
    void connect();
}

class MySQLDatabase implements Database {
    public void connect() {
        // MySQL logic
    }
}

class PostgreSQLDatabase implements Database {
    public void connect() {
        // PostgreSQL logic
    }
}

class DataService {
    private Database database;

    public DataService(Database database) {
        this.database = database;
    }

    public void fetchData() {
        database.connect();
    }
}
```

## Summary

| Principle | Focus | Problem Solved |
|-----------|-------|----------------|
| **SRP** | One class = one job | Avoids large, unmanageable classes |
| **OCP** | Extend without change | Avoids breaking existing code |
| **LSP** | Substitutability | Avoids unexpected behavior in polymorphism |
| **ISP** | Specific interfaces | Avoids forced implementation |
| **DIP** | Abstraction | Promotes loose coupling |

## Benefits of Following SOLID Principles

- **Maintainability:** Code is easier to maintain and modify
- **Testability:** Individual components can be tested in isolation
- **Flexibility:** New features can be added with minimal changes
- **Reusability:** Components can be reused across different parts of the application
- **Readability:** Code is more organized and easier to understand