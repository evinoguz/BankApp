package com.sekom.bankapp.dataAccess.abstracts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sekom.bankapp.entities.concretes.AccountUser;

public interface AccountUserDao extends JpaRepository<AccountUser, Integer>{
	
	@Query("From AccountUser where customer.customerNumber=:customerNumber")
	List<AccountUser> getByCustomerNumber(String customerNumber);
	
	@Query("From AccountUser where customer.customerId=:customerId")
	List<AccountUser> findByCustomerId(int customerId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
    @Query("UPDATE AccountUser SET balance=:balance WHERE customer.customerId=:customerId")
	int updateBalanceCustomerId(double balance,int customerId);

}
