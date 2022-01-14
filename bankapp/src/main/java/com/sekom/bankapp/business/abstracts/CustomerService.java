package com.sekom.bankapp.business.abstracts;
import java.util.List;
import com.sekom.bankapp.core.utilities.DataResult;
import com.sekom.bankapp.entities.concretes.Customer;

public interface CustomerService {
	DataResult<List<Customer>>getall();
}
