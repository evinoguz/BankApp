package com.sekom.bankapp.business.concretes;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sekom.bankapp.business.abstracts.AccountUserService;
import com.sekom.bankapp.core.utilities.DataResult;
import com.sekom.bankapp.core.utilities.ErrorResult;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.core.utilities.SuccessDataResult;
import com.sekom.bankapp.core.utilities.SuccessResult;
import com.sekom.bankapp.dataAccess.abstracts.AccountUserDao;
import com.sekom.bankapp.dataAccess.abstracts.BankDao;
import com.sekom.bankapp.dataAccess.abstracts.CustomerDao;
import com.sekom.bankapp.entities.concretes.AccountUser;
import com.sekom.bankapp.entities.concretes.Bank;
import com.sekom.bankapp.entities.concretes.Customer;

@Service
public class AccountUsersManager implements AccountUserService{

	private AccountUserDao accountDao;
	private CustomerDao customerDao;
	private BankDao bankDao;

	@Autowired
	public AccountUsersManager(AccountUserDao accountDao,CustomerDao customerDao,BankDao bankDao) {
		super();
		this.accountDao = accountDao;
		this.customerDao = customerDao;
		this.bankDao = bankDao;
	}
	
	@Override
	public DataResult<List<AccountUser>> getall() {
		Sort sort=Sort.by(Sort.Direction.DESC,"accountId");
		return new SuccessDataResult<List<AccountUser>>
		(this.accountDao.findAll(sort),"Data listed");
	}

	@Override
	public Result add(String customerNumber,String bankName,double balance) {
		
		List<Customer> customer=this.customerDao.getByCustomerNumber(customerNumber);
		List<Bank> bank=this.bankDao.getByBankName(bankName);
		if(bank.isEmpty()) {//banka varsa
			return new ErrorResult("Bank name is invalid");
		}
		if(customer.isEmpty()) {
			return new ErrorResult("Customer name is invalid");
		}
		if(!this.accountDao.findByCustomerId(customer.get(0).getCustomerId()).isEmpty())
	    {
			return new ErrorResult("The account already exists..");
		}
	    
		AccountUser account=new AccountUser();
		account.setCustomer(customer.get(0));
		account.setBank(bank.get(0));
		account.setBalance(balance);
		account.setCreatedDate(LocalDateTime.now());
		this.accountDao.save(account);
		return new SuccessResult("Account created");
		
	}

}
