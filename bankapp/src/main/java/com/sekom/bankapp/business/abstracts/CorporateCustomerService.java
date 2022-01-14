package com.sekom.bankapp.business.abstracts;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.entities.concretes.CorporateCustomer;

public interface CorporateCustomerService {
	Result add(CorporateCustomer corporate);
}
