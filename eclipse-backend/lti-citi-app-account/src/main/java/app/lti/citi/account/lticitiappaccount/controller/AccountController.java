package app.lti.citi.account.lticitiappaccount.controller;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.lti.citi.account.lticitiappaccount.dto.AccountDetailDto;
import app.lti.citi.account.lticitiappaccount.dto.AccountExceptionDto;
import app.lti.citi.account.lticitiappaccount.exception.CommonException;
import app.lti.citi.account.lticitiappaccount.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService service; 

	@PostMapping("/create")
	public ResponseEntity<AccountDetailDto> createAccount(@RequestBody AccountDetailDto dto){
		
		AccountDetailDto account = null;
		
		if(dto == null)
			throw new CommonException("Invalid account request to create.");
		
		account = this.service.createAccount(dto);
		
		if(account == null)
			throw new CommonException("Account could not be created.");
		
		ResponseEntity<AccountDetailDto> response = new ResponseEntity<AccountDetailDto>(account, HttpStatus.OK);
		
		return response; 
	}
	
	@PutMapping("/modify")
	public ResponseEntity<AccountDetailDto> modifyAccount(@RequestBody AccountDetailDto dto){
		
		AccountDetailDto account = null;
		
		if(dto == null)
			throw new CommonException("Invalid account request to modify.");
		
		account = this.service.modifyAccount(dto);
		
		if(account == null)
			throw new CommonException("Account could not be modified.");
		
		ResponseEntity<AccountDetailDto> response = new ResponseEntity<AccountDetailDto>(account, HttpStatus.OK);
		
		return response; 
	}
	
	@DeleteMapping("/delete/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable("accountId") String accountId){
		
		String responseMessage = null;
		
		if(accountId == null)
			throw new CommonException("Invalid account request to delete.");
		
		boolean deleteResponse = this.service.deleteAccount(accountId);
		
		if(deleteResponse)
			responseMessage = "Account deactivated successfully.";
		else
			throw new CommonException("Account deactivation failed.");
		
		ResponseEntity<String> response = new ResponseEntity<String>(responseMessage, HttpStatus.OK);
		
		return response; 
	}
	
	@GetMapping("/get-amount/{accountId}")
	public ResponseEntity<Double> getAmount(@PathVariable("accountId") String accountId){
		Double amount = 0.0d;
		
		if(accountId == null)
			throw new CommonException("Invalid account request to delete.");
		
		try {
			amount = this.service.getAmount(accountId);
		}catch (Exception e) {
			throw new CommonException(e.getMessage());
		}
		
		ResponseEntity<Double> response = new ResponseEntity<Double>(amount, HttpStatus.OK);
		
		return response; 
	}
	
	@PostMapping("/update-amount/{accountId}/{amount}")
	public ResponseEntity<AccountDetailDto> updateAccountAmount(@PathVariable("accountId") String accountId, @PathVariable("amount") Double amount){
		AccountDetailDto account = null;
		
		if(accountId == null)
			throw new CommonException("Invalid account number.");
		
		try {
			account = this.service.updateAccountAmount(accountId, amount);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CommonException(e.getMessage());
		}
		
		if(account == null)
			throw new CommonException("Amount could not be updated.");
		
		ResponseEntity<AccountDetailDto> response = new ResponseEntity<AccountDetailDto>(account, HttpStatus.OK);
		
		return response; 
	} 
	
	@ExceptionHandler(CommonException.class)// It is used to handle exception in rest controller - if not defined, then in return response a trace element will also be attached with exception
	public ResponseEntity<AccountExceptionDto> commonExceptionHandler(CommonException ex){
		
		logger.error(ex.getMessage());
		
		AccountExceptionDto exceptionDto = new AccountExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<AccountExceptionDto> response = new ResponseEntity<AccountExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
}
