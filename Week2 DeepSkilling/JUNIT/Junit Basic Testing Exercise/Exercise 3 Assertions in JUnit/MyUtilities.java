package com.example.LearningJUNIT;

import java.util.Arrays;

public class MyUtilities {
    public int add(int a, int b) {
        return a + b;
    }

    public boolean isEven(int a) {
        return a % 2 == 0;
    }

    public String getUserById(int id) {
        return id == 1 ? "Alice" : null;
    }

    public int[] sortArray(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    public int divide(int a, int b) {
        return a / b;
    }
}
