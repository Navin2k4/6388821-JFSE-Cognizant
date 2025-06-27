package com.example.LearningJUNITCompleteAdvanced;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    @Test
    @Order(1)
    void testInitialization() {
        System.out.println("Initialization step");
    }

    @Test
    @Order(2)
    void testExecution() {
        System.out.println("Execution step");
    }

    @Test
    @Order(3)
    void testCleanup() {
        System.out.println("Cleanup step");
    }
}
