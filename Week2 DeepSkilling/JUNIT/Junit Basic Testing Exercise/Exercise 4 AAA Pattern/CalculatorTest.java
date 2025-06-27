package com.example.LearningJUNIT;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("Calculator Initialized");
    }

    @AfterEach
    void tearDown() {
        calc = null;
        System.out.println("Calculator cleaned up");
    }

    @Test
    public void testAddition() {
        // Arrange done in @Before

        // Act
        int result = calc.add(3, 2);

        // Assert
        assertEquals(5, result);
    }

    @Test
    public void testSubtraction() {
        int result = calc.subtract(10, 5);
        assertEquals(5, result);
    }

    @Test
    public void testMultiplication() {
        int result = calc.multiply(4, 3);
        assertEquals(12, result);
    }

    @Test
    public void testDivision() {
        int result = calc.divide(10, 2);
        assertEquals(5, result);
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(10, 0);
        });
    }
}