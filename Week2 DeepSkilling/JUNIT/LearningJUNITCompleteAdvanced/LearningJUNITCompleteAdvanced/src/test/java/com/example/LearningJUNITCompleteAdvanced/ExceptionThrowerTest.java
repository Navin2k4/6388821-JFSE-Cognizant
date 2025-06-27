package com.example.LearningJUNITCompleteAdvanced;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExceptionThrowerTest {
    @Test
    public void testExceptionIsThrown() {
        ExceptionThrower thrower = new ExceptionThrower();
        assertThrows(IllegalArgumentException.class, thrower::throwException);
    }
}