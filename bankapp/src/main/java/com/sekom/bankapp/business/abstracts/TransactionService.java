package com.sekom.bankapp.business.abstracts;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.entities.concretes.AccountUser;

public interface TransactionService {
	//Result add(AccountUser account, double amount,String message,String action);
	Result transactionHistory(String customerNumber);
	Result balanceInquiry(String customerNumber);
	Result deposit(String customerNumber,double amount);
	Result withdraw(String customerNumber,double amount);
}
