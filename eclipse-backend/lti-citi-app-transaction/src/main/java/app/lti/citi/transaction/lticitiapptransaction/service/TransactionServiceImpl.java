package app.lti.citi.transaction.lticitiapptransaction.service;

import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;

public class TransactionServiceImpl implements TransactionService{

	@Override
	public TransactionDetailDto transferAmount(TransactionDetailDto transactionDetailDto) throws Exception {
		
		/**
		 * Steps to follow in tranfer amount
		 * 1) check request is not null
		 * 2) fetch amount from sender's accountId
		 * 3) fetch amount from receiver's accountId
		 * 4) minus requested amount from sender's amount
		 * 5) add requested amount to receiver's amount
		 * 6) update minus amount to sender's account
		 * 7) update added amount to receiver's account
		 * 8) construct TransactionDetailDto with status = SUCCESS/ FAILURE 
		 * 9) return inserted TransactionDetail
		 * 
		 * 10) For step 2 to 7 create 5 Callable type classes
		 * 11) execute step 2 to 7 in same order with an atomic operation by imposing a lock. 
		 */
		
		TransactionDetailDto dto = null;
		
		if(transactionDetailDto != null) {
			
			
			
		}
		
		return dto;
	}

}
