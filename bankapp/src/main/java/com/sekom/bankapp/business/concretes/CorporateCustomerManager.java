package com.sekom.bankapp.business.concretes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sekom.bankapp.business.abstracts.CorporateCustomerService;
import com.sekom.bankapp.core.utilities.ErrorResult;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.core.utilities.SuccessResult;
import com.sekom.bankapp.dataAccess.abstracts.CorporateCustomerDao;
import com.sekom.bankapp.dataAccess.abstracts.CustomerDao;
import com.sekom.bankapp.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{

	private CorporateCustomerDao corporateDao;
	private CustomerDao customerDao;

	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateDao,CustomerDao customerDao) {
		super();
		this.corporateDao = corporateDao;
		this.customerDao = customerDao;
	}
	
	@Override
	public Result add(CorporateCustomer corporate) {
		if(!this.customerDao.findByCustomerNumber(corporate.getCustomerNumber()).isEmpty())
	    {
			return new ErrorResult("The customer number already exists..");
		}
		this.corporateDao.save(corporate);
		return new SuccessResult("Corporate Customer added");
	}
}