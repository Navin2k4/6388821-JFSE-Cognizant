package com.example.LearningJUNITCompleteAdvanced;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;

class PerformanceTesterTest {
    @Test
    public void testPerformance() {
        PerformanceTester tester = new PerformanceTester();
        assertTimeout(Duration.ofMillis(200), () -> tester.performTask());
    }
}