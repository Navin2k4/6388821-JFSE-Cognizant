package com.cognizant.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
public class LoanController {

    @GetMapping("/{number}")
    public LoanResponse getLoanDetail(@PathVariable String number) {
        return new LoanResponse(number, "car", 400000, 3258, 18);
    }

}
