package com.sekom.bankapp.business.concretes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sekom.bankapp.business.abstracts.CustomerService;
import com.sekom.bankapp.core.utilities.DataResult;
import com.sekom.bankapp.core.utilities.SuccessDataResult;
import com.sekom.bankapp.dataAccess.abstracts.CustomerDao;
import com.sekom.bankapp.entities.concretes.Customer;

@Service
public class CustomerManager implements CustomerService{

	private CustomerDao customerDao;
	@Autowired
	public CustomerManager(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public DataResult<List<Customer>> getall() {
		Sort sort=Sort.by(Sort.Direction.DESC,"customerId");
		return new SuccessDataResult<List<Customer>>
		(this.customerDao.findAll(sort),"Data listed");
	}
	
}
