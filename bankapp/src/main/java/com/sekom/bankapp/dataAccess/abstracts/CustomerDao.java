package com.sekom.bankapp.dataAccess.abstracts;
import com.sekom.bankapp.entities.concretes.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	@Query("From Customer where customerNumber=:customerNumber")
	List<Customer> findByCustomerNumber(String customerNumber);
	
	@Query("From Customer where customerNumber=:customerNumber")
	List<Customer> getByCustomerNumber(String customerNumber);
}
