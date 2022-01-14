package com.sekom.bankapp.dataAccess.abstracts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sekom.bankapp.entities.concretes.Bank;

public interface BankDao extends JpaRepository<Bank, Integer>{
	@Query("From Bank where bankName=:bankName")
	List<Bank> getByBankName(String bankName);
}
