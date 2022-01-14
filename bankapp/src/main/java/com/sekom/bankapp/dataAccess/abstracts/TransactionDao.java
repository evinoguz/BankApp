package com.sekom.bankapp.dataAccess.abstracts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sekom.bankapp.entities.concretes.Transaction;

public interface TransactionDao extends JpaRepository<Transaction, Integer>{
	@Query("From Transaction where account.customer.customerNumber=:customerNumber")
	List<Transaction> getByCustomerNumber(String customerNumber);
}
