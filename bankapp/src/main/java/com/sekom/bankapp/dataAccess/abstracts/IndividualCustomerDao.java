package com.sekom.bankapp.dataAccess.abstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sekom.bankapp.entities.concretes.IndividualCustomer;

public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer>{
	
}
