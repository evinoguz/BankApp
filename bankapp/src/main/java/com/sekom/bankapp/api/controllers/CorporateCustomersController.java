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
import com.sekom.bankapp.business.abstracts.CorporateCustomerService;
import com.sekom.bankapp.entities.concretes.CorporateCustomer;

@RestController
@RequestMapping(value="/api/corporateCustomers")
public class CorporateCustomersController {
	
	private CorporateCustomerService corporateService;
	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateService) {
		super();
		this.corporateService = corporateService;
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<?> add(@Valid @RequestBody CorporateCustomer corporate) {
		return ResponseEntity.ok(this.corporateService.add(corporate));
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
