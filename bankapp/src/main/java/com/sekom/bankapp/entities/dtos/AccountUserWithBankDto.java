package com.sekom.bankapp.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountUserWithBankDto {
	private int id;
	private String customerNumber;
	private String bankName;
}
