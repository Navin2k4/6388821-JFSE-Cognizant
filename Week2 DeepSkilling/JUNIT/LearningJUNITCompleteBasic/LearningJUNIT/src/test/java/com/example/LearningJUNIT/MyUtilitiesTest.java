package com.example.LearningJUNIT;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyUtilitiesTest {

    MyUtilities utils = new MyUtilities();

    @Test
    public void testAdd() {
        assertEquals(10, utils.add(4, 6));
        assertEquals(0, utils.add(-3, 3));
    }

    @Test
    public void testIsEven() {
        assertTrue(utils.isEven(4));
        assertFalse(utils.isEven(7));
    }

    @Test
    public void testGetUserById() {
        assertNotNull(utils.getUserById(1));
        assertNull(utils.getUserById(2));
    }

    @Test
    public void testSortArray() {
        int[] unsorted = {5, 2, 3, 1};
        int[] sorted = {1, 2, 3, 5};
        assertArrayEquals(sorted, utils.sortArray(unsorted));
    }

    @Test
    public void testDivide() {
        assertEquals(2, utils.divide(10, 5));
        assertThrows(ArithmeticException.class, () -> {
            utils.divide(10, 0);
        });
    }
}