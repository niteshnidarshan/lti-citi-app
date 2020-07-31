package app.lti.citi.transaction.lticitiapptransaction.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;
import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionExceptionDto;
import app.lti.citi.transaction.lticitiapptransaction.exception.CommonException;
import app.lti.citi.transaction.lticitiapptransaction.service.TransactionService;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private TransactionService service;
	
	
	@PostMapping("/transfer")
	public ResponseEntity<TransactionDetailDto> transferAmount(@RequestBody TransactionDetailDto dto){
		TransactionDetailDto transaction = null;
		
		if(dto == null) {
			throw new CommonException("Invalid transaction request!");
		}
		
		try {
			//check weather the account is same 
			if(dto.getSenderAccountId() != null && dto.getSenderAccountId().equals(dto.getReceiversAccountId()))
				throw new CommonException("Both the account provided same. Change sender/ benificier account#.");
			
			//validate sender's account balance
			Double sendersBalance = this.service.getAmount(dto.getSenderAccountId());
			
			if(dto.getAmount() <= 0)
				throw new CommonException("Not a valid amount to transfer");
			
			if(dto.getAmount() > sendersBalance)
				throw new CommonException("Not enough money in your account!");
			
			transaction = this.service.transferAmount(dto);
			
		} catch (Exception e) {
			throw new CommonException("Transaction failed ... "+e.getMessage());
		}
		
		
		
		ResponseEntity<TransactionDetailDto> response = new ResponseEntity<TransactionDetailDto>(transaction, HttpStatus.OK);
		return response;
	}
	
	@GetMapping("/get-all/{associatedUserId}")
	public ResponseEntity<List<TransactionDetailDto>> getAllTransaction(@PathVariable("associatedUserId") String associatedUserId){
		List<TransactionDetailDto> transactionList = null;
		
		if(associatedUserId == null) {
			throw new CommonException("Invalid request!");
		}
		
		try {
			
			transactionList = this.service.getAllTransaction(associatedUserId);
			
		} catch (Exception e) {
			throw new CommonException("Transaction failed ... "+e.getMessage());
		}
		
		
		
		ResponseEntity<List<TransactionDetailDto>> response = new ResponseEntity<List<TransactionDetailDto>>(transactionList, HttpStatus.OK);
		return response;
	}
	
	@ExceptionHandler(CommonException.class)// It is used to handle exception in rest controller - if not defined, then in return response a trace element will also be attached with exception
	public ResponseEntity<TransactionExceptionDto> commonExceptionHandler(CommonException ex){
		
		logger.error(ex.getMessage());
		
		TransactionExceptionDto exceptionDto = new TransactionExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<TransactionExceptionDto> response = new ResponseEntity<TransactionExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
}
