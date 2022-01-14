package com.sekom.bankapp.api.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.sekom.bankapp.core.utilities.DataResult;
import com.sekom.bankapp.core.utilities.ErrorDataResult;
import com.sekom.bankapp.business.abstracts.CustomerService;
import com.sekom.bankapp.entities.concretes.Customer;

@RestController
@RequestMapping(value="/api/customers")
public class CustomersController {
	
	private CustomerService customerService;
	@Autowired
	public CustomersController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<Customer>> getAll(){
		return this.customerService.getall();
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
