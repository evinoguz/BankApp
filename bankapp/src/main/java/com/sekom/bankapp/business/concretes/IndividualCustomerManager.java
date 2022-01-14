package com.sekom.bankapp.business.concretes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sekom.bankapp.business.abstracts.IndividualCustomerService;
import com.sekom.bankapp.core.utilities.ErrorResult;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.core.utilities.SuccessResult;
import com.sekom.bankapp.dataAccess.abstracts.CustomerDao;
import com.sekom.bankapp.dataAccess.abstracts.IndividualCustomerDao;
import com.sekom.bankapp.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService{

	private IndividualCustomerDao individualDao;
	private CustomerDao customerDao;
	
	@Autowired
	public IndividualCustomerManager(IndividualCustomerDao individualDao,CustomerDao customerDao) {
		super();
		this.individualDao = individualDao;
		this.customerDao = customerDao;
	}
	
	@Override
	public Result add(IndividualCustomer individual) {
		
		if(!this.customerDao.findByCustomerNumber(individual.getCustomerNumber()).isEmpty())
	    {
			return new ErrorResult("The customer number already exists..");
		}
		this.individualDao.save(individual);
		return new SuccessResult("Individual Customer added");
	}

}