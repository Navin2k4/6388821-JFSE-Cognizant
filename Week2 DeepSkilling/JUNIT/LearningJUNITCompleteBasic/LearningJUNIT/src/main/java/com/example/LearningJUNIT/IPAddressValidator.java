package com.example.LearningJUNIT;

public class IPAddressValidator {
    public static boolean isValid(String s) {
        String[] parts = s.split("\\.");
        if (parts.length != 4)
            return false;
        for (String part : parts) {
            if (part.isEmpty())
                return false;
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255)
                    return false;
                if (part.length() > 1 && part.startsWith("0"))
                    return false;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

//    Example of a Failure case
//    public static boolean isValid(String s) {
//        return s.equals("192.168.1.1");
//    }
}
