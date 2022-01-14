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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sekom.bankapp.core.utilities.ErrorDataResult;
import com.sekom.bankapp.business.abstracts.IndividualCustomerService;
import com.sekom.bankapp.entities.concretes.IndividualCustomer;

@RestController
@RequestMapping(value="/api/individualCustomers")
public class IndividualCustomersController {
	
	private IndividualCustomerService individualService;
	@Autowired
	public IndividualCustomersController(IndividualCustomerService individualService) {
		super();
		this.individualService = individualService;
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<?> add(@Valid @RequestBody IndividualCustomer customer) {
		return ResponseEntity.ok(this.individualService.add(customer));
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
