package com.sekom.bankapp.business.concretes;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.sekom.bankapp.business.abstracts.TransactionService;
import com.sekom.bankapp.core.utilities.ErrorResult;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.core.utilities.SuccessDataResult;
import com.sekom.bankapp.core.utilities.SuccessResult;
import com.sekom.bankapp.dataAccess.abstracts.AccountUserDao;
import com.sekom.bankapp.dataAccess.abstracts.CustomerDao;
import com.sekom.bankapp.dataAccess.abstracts.TransactionDao;
import com.sekom.bankapp.entities.concretes.AccountUser;
import com.sekom.bankapp.entities.concretes.Transaction;

@Service
public class TransactionIManager implements TransactionService{
	
	private TransactionDao transactionDao;
	private AccountUserDao accountDao;

	@Autowired
	public TransactionIManager(TransactionDao transactionDao,AccountUserDao accountDao,CustomerDao customerDao) {
		super();
		this.transactionDao = transactionDao;
		this.accountDao = accountDao;
	}
	
	//@Override
	public boolean add(AccountUser account, double amount,String message) {
		
		Transaction transaction=new Transaction();
		transaction.setAccount(account);
		transaction.setCreatedDate(LocalDateTime.now());
		transaction.setMessage(message);
		this.transactionDao.save(transaction);
		return true;
	}

	@Override
	public Result balanceInquiry(String customerNumber) {
		
		List<AccountUser> account=this.accountDao.getByCustomerNumber(customerNumber);
		if(account.isEmpty()) {
			return new ErrorResult("Customer name is invalid");
		}
		double balance=account.get(0).getBalance();
		return new SuccessResult("Total balance: "+balance);
		
	}

	@Override
	public Result deposit(String customerNumber, double amount) {
		List<AccountUser> accountIs=this.accountDao.getByCustomerNumber(customerNumber);
		if(accountIs.isEmpty()) {
			return new ErrorResult("Customer name is invalid");
		}		
		AccountUser account=new AccountUser();
		account.setCustomer(accountIs.get(0).getCustomer());
		account.setBank(accountIs.get(0).getBank());
		account.setBalance(accountIs.get(0).getBalance());
		
		int customerId=account.getCustomer().getCustomerId();
		double balance=account.getBalance();
		double lastBalance=balance+amount;
		
		this.accountDao.updateBalanceCustomerId(lastBalance, customerId);
		boolean action=this.add(this.accountDao.getByCustomerNumber(customerNumber).get(0), amount, "'"+customerNumber+"' $"+amount+" deposited");
		if(action) {
			return new SuccessResult("$"+amount+" deposited. Total balance: "+lastBalance);
		}
		return new ErrorResult("Deposit transaction failed");

	}

	@Override
	public Result withdraw(String customerNumber, double amount) {
		List<AccountUser> accountIs=this.accountDao.getByCustomerNumber(customerNumber);
		if(accountIs.isEmpty()) {
			return new ErrorResult("Customer name is invalid");
		}		
		AccountUser account=new AccountUser();
		account.setCustomer(accountIs.get(0).getCustomer());
		account.setBank(accountIs.get(0).getBank());
		account.setBalance(accountIs.get(0).getBalance());

		int customerId=account.getCustomer().getCustomerId();
		double balance=account.getBalance();
		if(amount>balance) {
			return new ErrorResult("Insufficient balance");
		}
		double lastBalance=balance-amount;
		
		this.accountDao.updateBalanceCustomerId(lastBalance, customerId);
		boolean action=this.add(this.accountDao.getByCustomerNumber(customerNumber).get(0), amount, "'"+customerNumber+"' $"+amount+" deposited");
		if(action) {
			return new SuccessResult("$"+amount+" withdrawn. Total balance: "+lastBalance);
		}
		return new ErrorResult("Withdrawn transaction failed");
	}

	@Override
	public Result transactionHistory(String customerNumber) {
		Sort sort=Sort.by(Sort.Direction.DESC,"transactionId");
		this.transactionDao.findAll(sort);
		return new SuccessDataResult<List<Transaction>>
		(this.transactionDao.getByCustomerNumber(customerNumber),"Data listed");
	}

}
