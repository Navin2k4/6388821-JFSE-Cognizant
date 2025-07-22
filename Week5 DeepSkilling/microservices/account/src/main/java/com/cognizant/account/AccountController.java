package com.cognizant.account;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    @GetMapping("/{number}")
    public AccountResponse getAccountDetail(@PathVariable String number) {
        return new AccountResponse(number, "savings", 12121212);
    }      
}
