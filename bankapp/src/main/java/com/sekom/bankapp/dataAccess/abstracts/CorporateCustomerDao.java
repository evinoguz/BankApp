package com.sekom.bankapp.dataAccess.abstracts;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sekom.bankapp.entities.concretes.CorporateCustomer;

public interface CorporateCustomerDao extends JpaRepository<CorporateCustomer, Integer>{
	
}
