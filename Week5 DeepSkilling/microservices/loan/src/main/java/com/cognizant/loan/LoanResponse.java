package com.cognizant.loan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {
    private String number;
    private String type;
    private double loan;
    private double emi;
    private double tenure;
}
