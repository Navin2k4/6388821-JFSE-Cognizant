# Design Patterns - Complete Guide

## Table of Contents
1. [Gang of Four (GoF) Overview](#gang-of-four-gof-overview)
2. [Creational Patterns](#creational-patterns)
3. [Structural Patterns](#structural-patterns)
4. [Behavioral Patterns](#behavioral-patterns)
5. [Architectural Patterns](#architectural-patterns)

---

## Gang of Four (GoF) Overview

The Gang of Four (GoF) design patterns are 23 classic design patterns described in the book "Design Patterns: Elements of Reusable Object-Oriented Software" by Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides.

### Pattern Categories:
- **Creational Patterns:** Deal with object creation mechanisms
- **Structural Patterns:** Deal with object composition and relationships
- **Behavioral Patterns:** Deal with communication between objects and assignment of responsibilities

---

# Creational Patterns

## 1. Singleton Pattern

**Purpose:** Ensures a class has only one instance and provides global access to it.

**When to Use:**
- Database connections
- Logger classes
- Configuration settings
- Thread pools

### Implementation

```java
public class Singleton {
    // Thread-safe singleton using enum (recommended)
    public enum SingletonEnum {
        INSTANCE;
        
        public void doSomething() {
            System.out.println("Doing something...");
        }
    }
    
    // Traditional thread-safe implementation
    private static volatile Singleton instance;
    
    private Singleton() {
        // Private constructor prevents instantiation
    }
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        // Using enum singleton (preferred)
        Singleton.SingletonEnum.INSTANCE.doSomething();
        
        // Using traditional singleton
        Singleton singleton = Singleton.getInstance();
    }
}
```

### Pros and Cons

**Pros:**
- Controlled access to sole instance
- Reduced memory footprint
- Global access point

**Cons:**
- Difficult to unit test
- Violates Single Responsibility Principle
- Can create tight coupling

---

## 2. Factory Method Pattern

**Purpose:** Creates objects without specifying their exact classes. Delegates object creation to subclasses.

**When to Use:**
- When you don't know beforehand the exact types of objects your code should work with
- When you want to provide users with a way to extend internal components

### Implementation

```java
// Product interface
interface Animal {
    void makeSound();
}

// Concrete products
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Meow!");
    }
}

// Creator abstract class
abstract class AnimalFactory {
    // Factory method
    public abstract Animal createAnimal();
    
    // Template method using factory method
    public void processAnimal() {
        Animal animal = createAnimal();
        animal.makeSound();
    }
}

// Concrete creators
class DogFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}

class CatFactory extends AnimalFactory {
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        AnimalFactory dogFactory = new DogFactory();
        AnimalFactory catFactory = new CatFactory();
        
        dogFactory.processAnimal(); // Output: Woof!
        catFactory.processAnimal(); // Output: Meow!
    }
}
```

### Benefits
- Eliminates tight coupling between creator and concrete products
- Single Responsibility Principle
- Open/Closed Principle

---

## 3. Builder Pattern

**Purpose:** Constructs complex objects step by step. Allows you to produce different types and representations of an object using the same construction code.

**When to Use:**
- Creating objects with many optional parameters
- When constructor has too many parameters
- When you want to create different representations of the same object

### Implementation

```java
// Product class
class Computer {
    private String CPU;
    private String RAM;
    private String storage;
    private String GPU;
    private boolean hasWiFi;
    private boolean hasBluetooth;
    
    // Private constructor
    private Computer(ComputerBuilder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
    }
    
    @Override
    public String toString() {
        return "Computer{" +
                "CPU='" + CPU + '\'' +
                ", RAM='" + RAM + '\'' +
                ", storage='" + storage + '\'' +
                ", GPU='" + GPU + '\'' +
                ", hasWiFi=" + hasWiFi +
                ", hasBluetooth=" + hasBluetooth +
                '}';
    }
    
    // Static nested builder class
    public static class ComputerBuilder {
        // Required parameters
        private String CPU;
        private String RAM;
        
        // Optional parameters
        private String storage = "256GB SSD";
        private String GPU = "Integrated";
        private boolean hasWiFi = false;
        private boolean hasBluetooth = false;
        
        public ComputerBuilder(String CPU, String RAM) {
            this.CPU = CPU;
            this.RAM = RAM;
        }
        
        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }
        
        public ComputerBuilder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }
        
        public ComputerBuilder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }
        
        public ComputerBuilder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }
        
        public Computer build() {
            return new Computer(this);
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.ComputerBuilder("Intel i9", "32GB")
                .setStorage("1TB NVMe SSD")
                .setGPU("RTX 4080")
                .setWiFi(true)
                .setBluetooth(true)
                .build();
        
        Computer officePC = new Computer.ComputerBuilder("Intel i5", "16GB")
                .setWiFi(true)
                .build();
        
        System.out.println(gamingPC);
        System.out.println(officePC);
    }
}
```

### Benefits
- Readable and maintainable code
- Immutable objects
- Step-by-step construction
- Different representations of the same object

---

# Structural Patterns

## 1. Adapter Pattern

**Purpose:** Allows incompatible interfaces to work together. Acts as a bridge between two incompatible interfaces.

**When to Use:**
- When you want to use an existing class with an incompatible interface
- When you want to create a reusable class that cooperates with unrelated classes

### Implementation

```java
// Target interface (what client expects)
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Adaptee (existing incompatible interface)
interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

// Concrete adaptee implementations
class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file: " + fileName);
    }
    
    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

class Mp4Player implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }
    
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file: " + fileName);
    }
}

// Adapter class
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedPlayer;
    
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer = new Mp4Player();
        }
    }
    
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedPlayer.playMp4(fileName);
        }
    }
}

// Context class
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;
    
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc") || 
                   audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        
        audioPlayer.play("mp3", "song.mp3");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("vlc", "movie.vlc");
        audioPlayer.play("avi", "movie.avi");
    }
}
```

### Benefits
- Allows incompatible classes to work together
- Promotes code reuse
- Separates interface conversion logic from business logic

---

## 2. Decorator Pattern

**Purpose:** Adds new functionality to objects dynamically without altering their existing structure.

**When to Use:**
- When you want to add responsibilities to objects without subclassing
- When extension by subclassing is impractical

### Implementation

```java
// Component interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    
    @Override
    public double getCost() {
        return 2.0;
    }
}

// Base decorator
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription();
    }
    
    @Override
    public double getCost() {
        return coffee.getCost();
    }
}

// Concrete decorators
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Milk";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Sugar";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.2;
    }
}

class WhipDecorator extends CoffeeDecorator {
    public WhipDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return coffee.getDescription() + ", Whip";
    }
    
    @Override
    public double getCost() {
        return coffee.getCost() + 0.7;
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
        
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
        
        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
        
        coffee = new WhipDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
```

### Benefits
- More flexible than inheritance
- Allows behavior modification at runtime
- Follows Single Responsibility Principle
- Supports Open/Closed Principle

---

## 3. Proxy Pattern

**Purpose:** Provides a placeholder or surrogate for another object to control access to it.

**When to Use:**
- Lazy initialization (virtual proxy)
- Access control (protection proxy)
- Caching (caching proxy)
- Logging, monitoring

### Implementation

```java
// Subject interface
interface Image {
    void display();
}

// Real subject
class RealImage implements Image {
    private String fileName;
    
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }
    
    private void loadFromDisk() {
        System.out.println("Loading " + fileName + " from disk...");
        // Simulate expensive operation
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    
    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// Protection Proxy example
interface BankAccount {
    void withdraw(double amount);
    void deposit(double amount);
    double getBalance();
}

class RealBankAccount implements BankAccount {
    private double balance;
    
    public RealBankAccount(double initialBalance) {
        this.balance = initialBalance;
    }
    
    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount + ", Balance: $" + balance);
        } else {
            System.out.println("Insufficient funds");
        }
    }
    
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount + ", Balance: $" + balance);
    }
    
    @Override
    public double getBalance() {
        return balance;
    }
}

class BankAccountProxy implements BankAccount {
    private RealBankAccount realAccount;
    private String userRole;
    
    public BankAccountProxy(RealBankAccount realAccount, String userRole) {
        this.realAccount = realAccount;
        this.userRole = userRole;
    }
    
    @Override
    public void withdraw(double amount) {
        if ("ADMIN".equals(userRole) || "OWNER".equals(userRole)) {
            realAccount.withdraw(amount);
        } else {
            System.out.println("Access denied: Insufficient privileges to withdraw");
        }
    }
    
    @Override
    public void deposit(double amount) {
        realAccount.deposit(amount);
    }
    
    @Override
    public double getBalance() {
        if ("ADMIN".equals(userRole) || "OWNER".equals(userRole)) {
            return realAccount.getBalance();
        } else {
            System.out.println("Access denied: Cannot view balance");
            return 0;
        }
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        // Virtual Proxy example
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        
        System.out.println("--- First display call ---");
        image1.display(); // Loading occurs here
        
        System.out.println("--- Second display call ---");
        image1.display(); // No loading, uses cached object
        
        // Protection Proxy example
        RealBankAccount account = new RealBankAccount(1000);
        BankAccount userProxy = new BankAccountProxy(account, "USER");
        BankAccount adminProxy = new BankAccountProxy(account, "ADMIN");
        
        System.out.println("\n--- User operations ---");
        userProxy.deposit(200);
        userProxy.withdraw(100); // Access denied
        userProxy.getBalance();  // Access denied
        
        System.out.println("\n--- Admin operations ---");
        adminProxy.withdraw(100);
        System.out.println("Balance: $" + adminProxy.getBalance());
    }
}
```

### Types of Proxies
- **Virtual Proxy:** Lazy initialization
- **Protection Proxy:** Access control
- **Remote Proxy:** Represents object in different address space
- **Caching Proxy:** Caches results of expensive operations

---

# Behavioral Patterns

## 1. Observer Pattern

**Purpose:** Defines a one-to-many dependency between objects so that when one object changes state, all dependents are notified automatically.

**When to Use:**
- When changes to one object require changing many objects
- When an object should notify other objects without making assumptions about who those objects are

### Implementation

```java
import java.util.*;

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Display interface
interface DisplayElement {
    void display();
}

// Concrete subject
class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;
    
    public WeatherData() {
        observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }
    
    public void measurementsChanged() {
        notifyObservers();
    }
    
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}

// Concrete observers
class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;
    
    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity");
    }
}

class StatisticsDisplay implements Observer, DisplayElement {
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;
    private Subject weatherData;
    
    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    
    @Override
    public void update(float temperature, float humidity, float pressure) {
        tempSum += temperature;
        numReadings++;
        
        if (temperature > maxTemp) {
            maxTemp = temperature;
        }
        
        if (temperature < minTemp) {
            minTemp = temperature;
        }
        
        display();
    }
    
    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        
        weatherData.setMeasurements(25.0f, 65.0f, 30.4f);
        weatherData.setMeasurements(27.0f, 70.0f, 29.2f);
        weatherData.setMeasurements(23.0f, 90.0f, 29.2f);
    }
}
```

### Benefits
- Loose coupling between subject and observers
- Dynamic relationships
- Supports broadcast communication

---

## 2. Strategy Pattern

**Purpose:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable at runtime.

**When to Use:**
- When you have multiple ways to perform a task
- When you want to avoid conditional statements
- When algorithms should be easily interchangeable

### Implementation

```java
// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;
    
    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in " + 
                          cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}

class BankTransferPayment implements PaymentStrategy {
    private String accountNumber;
    
    public BankTransferPayment(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Bank Transfer from account: " + 
                          accountNumber.substring(0, 4) + "****");
    }
}

// Context class
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("Please select a payment method");
            return;
        }
        paymentStrategy.pay(amount);
    }
}

// Another example: Sorting strategies
interface SortingStrategy {
    void sort(int[] array);
}

class BubbleSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Bubble Sort");
        // Bubble sort implementation
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

class QuickSort implements SortingStrategy {
    @Override
    public void sort(int[] array) {
        System.out.println("Sorting using Quick Sort");
        quickSort(array, 0, array.length - 1);
    }
    
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }
    
    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        
        return i + 1;
    }
}

class SortContext {
    private SortingStrategy strategy;
    
    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void sort(int[] array) {
        strategy.sort(array);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        // Payment example
        ShoppingCart cart = new ShoppingCart();
        
        cart.setPaymentStrategy(new CreditCardPayment("1234567890123456", "John Doe"));
        cart.checkout(100.0);
        
        cart.setPaymentStrategy(new PayPalPayment("john@example.com"));
        cart.checkout(75.0);
        
        cart.setPaymentStrategy(new BankTransferPayment("9876543210"));
        cart.checkout(200.0);
        
        // Sorting example
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        SortContext context = new SortContext();
        
        context.setStrategy(new BubbleSort());
        context.sort(array.clone());
        
        context.setStrategy(new QuickSort());
        context.sort(array.clone());
    }
}
```

### Benefits
- Eliminates conditional statements
- Algorithms are easily interchangeable
- Follows Open/Closed Principle
- Easy to add new strategies

---

## 3. Command Pattern

**Purpose:** Encapsulates a request as an object, allowing you to parameterize clients with different requests, queue operations, and support undo operations.

### Complete Implementation

```java
// Command interface
interface Command {
    void execute();
    void undo();
}

// Receiver classes
class Light {
    private String location;
    private boolean isOn = false;
    
    public Light(String location) {
        this.location = location;
    }
    
    public void on() {
        isOn = true;
        System.out.println(location + " light is ON");
    }
    
    public void off() {
        isOn = false;
        System.out.println(location + " light is OFF");
    }
    
    public boolean isOn() {
        return isOn;
    }
}

class Stereo {
    private String location;
    private boolean isOn = false;
    private int volume = 0;
    
    public Stereo(String location) {
        this.location = location;
    }
    
    public void on() {
        isOn = true;
        System.out.println(location + " stereo is ON");
    }
    
    public void off() {
        isOn = false;
        System.out.println(location + " stereo is OFF");
    }
    
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println(location + " stereo volume set to " + volume);
    }
    
    public int getVolume() {
        return volume;
    }
    
    public boolean isOn() {
        return isOn;
    }
}

// Concrete commands
class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.on();
    }
    
    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.off();
    }
    
    @Override
    public void undo() {
        light.on();
    }
}

class StereoOnWithVolumeCommand implements Command {
    private Stereo stereo;
    private int previousVolume;
    
    public StereoOnWithVolumeCommand(Stereo stereo) {
        this.stereo = stereo;
    }
    
    @Override
    public void execute() {
        previousVolume = stereo.getVolume();
        stereo.on();
        stereo.setVolume(11);
    }
    
    @Override
    public void undo() {
        stereo.off();
        stereo.setVolume(previousVolume);
    }
}

// Null Object pattern for empty slots
class NoCommand implements Command {
    @Override
    public void execute() {}
    
    @Override
    public void undo() {}
}

// Macro command
class MacroCommand implements Command {
    private Command[] commands;
    
    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }
    
    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }
    
    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].undo();
        }
    }
}

// Invoker
class RemoteControl {
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;
    
    public RemoteControl() {
        onCommands = new Command[7];
        offCommands = new Command[7];
        
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
    }
    
    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    
    public void onButtonPressed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
    }
    
    public void offButtonPressed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
    }
    
    public void undoButtonPressed() {
        undoCommand.undo();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            sb.append("[slot ").append(i).append("] ")
              .append(onCommands[i].getClass().getName())
              .append("    ").append(offCommands[i].getClass().getName())
              .append("\n");
        }
        sb.append("[undo] ").append(undoCommand.getClass().getName()).append("\n");
        return sb.toString();
    }
}

// Usage example
class CommandPatternDemo {
    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl();
        
        // Create devices
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Stereo stereo = new Stereo("Living Room");
        
        // Create commands
        LightOnCommand livingRoomLightOn = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOn = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOff = new LightOffCommand(kitchenLight);
        
        StereoOnWithVolumeCommand stereoOnWithVolume = new StereoOnWithVolumeCommand(stereo);
        
        // Set commands to remote
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);
        remote.setCommand(2, stereoOnWithVolume, new NoCommand());
        
        // Create macro command
        Command[] partyOn = {livingRoomLightOn, stereoOnWithVolume};
        Command[] partyOff = {livingRoomLightOff};
        
        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);
        
        remote.setCommand(3, partyOnMacro, partyOffMacro);
        
        System.out.println(remote);
        
        // Test the remote
        remote.onButtonPressed(0);
        remote.offButtonPressed(0);
        remote.undoButtonPressed();
        
        remote.onButtonPressed(3);  // Party mode on
        remote.undoButtonPressed(); // Undo party mode
    }
}
```

### Command Pattern Benefits

1. **Decoupling**: Separates the object that invokes the operation from the object that performs it
2. **Parameterization**: Objects can be parameterized with different requests
3. **Queuing**: Commands can be stored and executed later
4. **Logging**: Commands can be logged for audit trails
5. **Undo/Redo**: Easy to implement undo and redo functionality
6. **Macro Commands**: Multiple commands can be combined

### Use Cases

- GUI buttons and menu items
- Thread pools and job queues
- Transactional behavior and rollback operations
- Wizards and multi-step processes
- Remote procedure calls
- Progress bars and operation tracking

---

## Architectural Patterns

### 1. Model-View-Controller (MVC) Pattern

**Purpose:** Separates an application into three interconnected components to separate internal representations from how information is presented to the user.

#### Components

**Model**: Represents data and business logic
- Contains the application's data and state
- Implements business rules and validation
- Notifies observers of state changes
- Independent of user interface

**View**: Represents the presentation layer
- Displays data from the model to the user
- Handles user interface rendering
- Can have multiple views for the same model
- Should not contain business logic

**Controller**: Handles user input and coordinates between Model and View
- Processes user input and gestures
- Updates the model based on user actions
- Selects appropriate view for response
- Contains application flow logic

#### MVC Implementation Example

```java
// Model
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private String rollNo;
    private List<Observer> observers = new ArrayList<>();
    
    public Student(String name, String rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }
    
    public String getRollNo() {
        return rollNo;
    }
    
    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
        notifyObservers();
    }
    
    // Observer pattern implementation
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// Observer interface for view updates
interface Observer {
    void update();
}

// View
class StudentView implements Observer {
    private Student model;
    
    public StudentView(Student model) {
        this.model = model;
        model.addObserver(this);
    }
    
    public void displayStudentDetails() {
        System.out.println("Student Details:");
        System.out.println("Name: " + model.getName());
        System.out.println("Roll No: " + model.getRollNo());
        System.out.println("------------------------");
    }
    
    @Override
    public void update() {
        System.out.println("View updated due to model change");
        displayStudentDetails();
    }
}

// Alternative view
class StudentTableView implements Observer {
    private Student model;
    
    public StudentTableView(Student model) {
        this.model = model;
        model.addObserver(this);
    }
    
    public void displayAsTable() {
        System.out.println("| Name | Roll No |");
        System.out.println("|------|---------|");
        System.out.printf("| %s | %s |\n", model.getName(), model.getRollNo());
    }
    
    @Override
    public void update() {
        System.out.println("Table view updated");
        displayAsTable();
    }
}

// Controller
class StudentController {
    private Student model;
    private StudentView view;
    
    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    
    public void setStudentName(String name) {
        model.setName(name);
    }
    
    public void setStudentRollNo(String rollNo) {
        model.setRollNo(rollNo);
    }
    
    public String getStudentName() {
        return model.getName();
    }
    
    public String getStudentRollNo() {
        return model.getRollNo();
    }
    
    public void updateView() {
        view.displayStudentDetails();
    }
    
    // Handle complex business operations
    public void validateAndUpdateStudent(String name, String rollNo) {
        if (name != null && !name.trim().isEmpty()) {
            model.setName(name);
        } else {
            System.out.println("Invalid name provided");
        }
        
        if (rollNo != null && rollNo.matches("\\d+")) {
            model.setRollNo(rollNo);
        } else {
            System.out.println("Invalid roll number provided");
        }
    }
}

// Usage
class MVCDemo {
    public static void main(String[] args) {
        // Create model
        Student student = new Student("John Doe", "123");
        
        // Create views
        StudentView standardView = new StudentView(student);
        StudentTableView tableView = new StudentTableView(student);
        
        // Create controller
        StudentController controller = new StudentController(student, standardView);
        
        // Initial display
        controller.updateView();
        
        // Update through controller
        controller.setStudentName("Jane Smith");
        controller.setStudentRollNo("456");
        
        // Validation example
        controller.validateAndUpdateStudent("", "invalid");
        controller.validateAndUpdateStudent("Bob Johnson", "789");
    }
}
```

#### MVC Variants

**MVP (Model-View-Presenter)**
- Presenter handles all UI logic
- View is more passive
- Better testability

**MVVM (Model-View-ViewModel)**
- ViewModel acts as binding layer
- Two-way data binding
- Popular in frameworks like Angular, WPF

#### MVC Benefits

1. **Separation of Concerns**: Clear separation between data, presentation, and logic
2. **Reusability**: Models can be reused across different views
3. **Maintainability**: Changes in one component don't affect others
4. **Testability**: Each component can be tested independently
5. **Multiple Views**: Same data can be presented in different ways
6. **Parallel Development**: Different teams can work on different components

#### MVC Use Cases

- Web applications (Spring MVC, ASP.NET MVC)
- Desktop applications
- Mobile applications
- API design
- Content management systems

---

### 2. Dependency Injection Pattern

**Purpose:** A technique for achieving Inversion of Control (IoC) between classes and their dependencies. Instead of a class creating its dependencies, they are provided (injected) from external sources.

#### Types of Dependency Injection

**Constructor Injection**: Dependencies are provided through class constructors
**Setter Injection**: Dependencies are provided through setter methods
**Interface Injection**: Dependencies are provided through interface methods

#### Implementation Examples

```java
// Without Dependency Injection (Tightly Coupled)
class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

class OrderService {
    private EmailService emailService = new EmailService(); // Tight coupling
    
    public void processOrder(String order) {
        System.out.println("Processing order: " + order);
        emailService.sendEmail("Order processed: " + order);
    }
}

// With Dependency Injection (Loosely Coupled)

// 1. Define interfaces
interface NotificationService {
    void sendNotification(String message);
}

interface PaymentService {
    boolean processPayment(double amount);
}

interface OrderRepository {
    void save(Order order);
    Order findById(String id);
}

// 2. Implement concrete classes
class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Email notification: " + message);
    }
}

class SMSNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("SMS notification: " + message);
    }
}

class CreditCardPaymentService implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        return true; // Simulate successful payment
    }
}

class PayPalPaymentService implements PaymentService {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        return true; // Simulate successful payment
    }
}

class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Saving order to database: " + order.getId());
    }
    
    @Override
    public Order findById(String id) {
        return new Order(id, "Sample Order", 100.0);
    }
}

// 3. Order class
class Order {
    private String id;
    private String description;
    private double amount;
    
    public Order(String id, String description, double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }
    
    // Getters
    public String getId() { return id; }
    public String getDescription() { return description; }
    public double getAmount() { return amount; }
}

// 4. Service with Constructor Injection
class ImprovedOrderService {
    private final NotificationService notificationService;
    private final PaymentService paymentService;
    private final OrderRepository orderRepository;
    
    // Constructor Injection
    public ImprovedOrderService(NotificationService notificationService,
                               PaymentService paymentService,
                               OrderRepository orderRepository) {
        this.notificationService = notificationService;
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
    }
    
    public void processOrder(Order order) {
        System.out.println("Processing order: " + order.getDescription());
        
        // Process payment
        boolean paymentSuccess = paymentService.processPayment(order.getAmount());
        
        if (paymentSuccess) {
            // Save order
            orderRepository.save(order);
            
            // Send notification
            notificationService.sendNotification(
                "Order " + order.getId() + " has been processed successfully"
            );
        } else {
            notificationService.sendNotification(
                "Order " + order.getId() + " payment failed"
            );
        }
    }
}

// 5. Service with Setter Injection
class OrderServiceWithSetterInjection {
    private NotificationService notificationService;
    private PaymentService paymentService;
    private OrderRepository orderRepository;
    
    // Setter Injection
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public void processOrder(Order order) {
        if (notificationService == null || paymentService == null || orderRepository == null) {
            throw new IllegalStateException("Dependencies not injected");
        }
        
        System.out.println("Processing order: " + order.getDescription());
        
        boolean paymentSuccess = paymentService.processPayment(order.getAmount());
        
        if (paymentSuccess) {
            orderRepository.save(order);
            notificationService.sendNotification(
                "Order " + order.getId() + " processed successfully"
            );
        }
    }
}

// 6. Simple Dependency Injection Container
class DIContainer {
    private Map<Class<?>, Object> services = new HashMap<>();
    private Map<Class<?>, Class<?>> serviceTypes = new HashMap<>();
    
    // Register service instance
    public <T> void registerInstance(Class<T> serviceClass, T implementation) {
        services.put(serviceClass, implementation);
    }
    
    // Register service type
    public <T> void registerType(Class<T> serviceClass, Class<? extends T> implementationClass) {
        serviceTypes.put(serviceClass, implementationClass);
    }
    
    // Resolve service
    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> serviceClass) {
        // Check if instance is already registered
        if (services.containsKey(serviceClass)) {
            return (T) services.get(serviceClass);
        }
        
        // Check if type is registered
        if (serviceTypes.containsKey(serviceClass)) {
            try {
                Class<?> implementationClass = serviceTypes.get(serviceClass);
                T instance = (T) implementationClass.getDeclaredConstructor().newInstance();
                services.put(serviceClass, instance); // Cache the instance
                return instance;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create instance", e);
            }
        }
        
        throw new RuntimeException("Service not registered: " + serviceClass.getName());
    }
    
    // Create service with constructor injection
    public <T> T createService(Class<T> serviceClass) {
        try {
            Constructor<?>[] constructors = serviceClass.getConstructors();
            if (constructors.length == 0) {
                return serviceClass.getDeclaredConstructor().newInstance();
            }
            
            Constructor<?> constructor = constructors[0]; // Take first constructor
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] parameters = new Object[parameterTypes.length];
            
            for (int i = 0; i < parameterTypes.length; i++) {
                parameters[i] = resolve(parameterTypes[i]);
            }
            
            return (T) constructor.newInstance(parameters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create service with dependencies", e);
        }
    }
}

// 7. Configuration and Usage
class DIDemo {
    public static void main(String[] args) {
        // Create DI container
        DIContainer container = new DIContainer();
        
        // Register services
        container.registerType(NotificationService.class, EmailNotificationService.class);
        container.registerType(PaymentService.class, CreditCardPaymentService.class);
        container.registerType(OrderRepository.class, DatabaseOrderRepository.class);
        
        // Create order service with dependencies injected
        ImprovedOrderService orderService = container.createService(ImprovedOrderService.class);
        
        // Create and process order
        Order order = new Order("ORD-001", "Laptop Computer", 999.99);
        orderService.processOrder(order);
        
        System.out.println("\n--- Switching to different implementations ---\n");
        
        // Switch to different implementations
        container.registerType(NotificationService.class, SMSNotificationService.class);
        container.registerType(Paym