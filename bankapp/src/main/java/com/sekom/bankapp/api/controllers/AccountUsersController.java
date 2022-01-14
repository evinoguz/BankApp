package com.sekom.bankapp.api.controllers;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sekom.bankapp.business.abstracts.AccountUserService;
import com.sekom.bankapp.core.utilities.ErrorDataResult;

@RestController
@RequestMapping(value="/api/acounts")
public class AccountUsersController {
	
	private AccountUserService accountService;
	@Autowired
	public AccountUsersController(AccountUserService accountService) {
		super();
		this.accountService = accountService;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid 
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("bankName") String bankName,
			@RequestParam("balance") double balance) {
		return ResponseEntity.ok(this.accountService.add(customerNumber,bankName,balance));
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
