package com.sekom.bankapp.business.abstracts;
import java.util.List;
import com.sekom.bankapp.core.utilities.DataResult;
import com.sekom.bankapp.core.utilities.Result;
import com.sekom.bankapp.entities.concretes.AccountUser;

public interface AccountUserService {
	DataResult<List<AccountUser>>getall();
	Result add(String customerNumber,String bankName,double balance);
}
