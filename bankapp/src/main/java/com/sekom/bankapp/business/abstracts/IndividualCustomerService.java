package com.sekom.bankapp.business.abstracts;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.entities.concretes.IndividualCustomer;

public interface IndividualCustomerService {
	Result add(IndividualCustomer individual);
}