package com.example.LearningJUNIT;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPAddressValidatorTest {

    @Test
    public void testValidIPAddresses() {
        assertTrue(IPAddressValidator.isValid("192.168.1.1"));
        assertTrue(IPAddressValidator.isValid("0.0.0.0"));
        assertTrue(IPAddressValidator.isValid("255.255.255.255"));
        assertTrue(IPAddressValidator.isValid("127.0.0.1"));
    }

    @Test
    public void testInvalidDueToFormat() {
        assertFalse(IPAddressValidator.isValid("192.168.1"));
        assertFalse(IPAddressValidator.isValid("192.168.1.1.1"));
        assertFalse(IPAddressValidator.isValid(""));
        assertFalse(IPAddressValidator.isValid("..1.1"));
    }

    @Test
    public void testInvalidDueToRange() {
        assertFalse(IPAddressValidator.isValid("256.0.0.1"));
        assertFalse(IPAddressValidator.isValid("192.168.300.1"));
        assertFalse(IPAddressValidator.isValid("-1.2.3.4"));
    }

    @Test
    public void testInvalidDueToLeadingZeros() {
        assertFalse(IPAddressValidator.isValid("01.2.3.4"));
        assertFalse(IPAddressValidator.isValid("192.168.001.1"));
    }

    @Test
    public void testInvalidDueToCharacters() {
        assertFalse(IPAddressValidator.isValid("abc.def.ghi.jkl"));
        assertFalse(IPAddressValidator.isValid("192.168.1.a"));
    }

}