package com.sekom.bankapp.api.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sekom.bankapp.core.utilities.DataResult;
import com.sekom.bankapp.core.utilities.ErrorDataResult;
import com.sekom.bankapp.business.abstracts.TransactionService;
import com.sekom.bankapp.entities.concretes.AccountUser;
import com.sekom.bankapp.entities.concretes.Transaction;
import com.sun.istack.NotNull;

@RestController
@RequestMapping(value="/api/transactions")
public class TransactionsController {
	
	private TransactionService transactionService;
	@Autowired
	public TransactionsController(TransactionService transactionService) {
		super();
		this.transactionService = transactionService;
	}
	/*@PostMapping(value="/add")
	public ResponseEntity<?> add(@NotBlank @NotNull 
			@RequestBody AccountUser account,
			@RequestParam("amount") double amount,
			@RequestParam("message") String message) {
		return ResponseEntity.ok(this.transactionService.add(account,amount,message));
	}
	*/
	@GetMapping("/balanceInquir")
	public ResponseEntity<?> balanceInquiry(@NotBlank @NotNull @RequestParam String customerNumber) {
		return ResponseEntity.ok(this.transactionService.balanceInquiry(customerNumber));
	}
	
	@PutMapping("/deposit")
	public ResponseEntity<?> deposit(@NotBlank @NotNull 
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("amount") double amount) {
		return ResponseEntity.ok(this.transactionService.deposit(customerNumber,amount));
	}
	
	@PutMapping("/withdraw")
	public ResponseEntity<?> withdraw(@NotBlank @NotNull 
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("amount") double amount) {
		return ResponseEntity.ok(this.transactionService.withdraw(customerNumber,amount));
	}
	
	@GetMapping("/transactionHistory")
	public ResponseEntity<?> transactionHistory(@Valid 
			@RequestParam("customerNumber") String customerNumber) {
		return ResponseEntity.ok(this.transactionService.transactionHistory(customerNumber));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException
	(MethodArgumentNotValidException exception){
		Map<String, String> validationErrors=new HashMap<String, String>();
		for(FieldError fieldError: exception.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors=new ErrorDataResult<Object>
		(validationErrors,"Doğrulama hatalı");
		return errors;
		
	}
}
