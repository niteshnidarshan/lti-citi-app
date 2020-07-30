package app.lti.citi.transaction.lticitiapptransaction.service;
  

import java.util.List;

import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;

public interface TransactionService {

	public TransactionDetailDto transferAmount(TransactionDetailDto transferDetailDto) throws Exception;
	public List<TransactionDetailDto> getAllTransaction(String associatedUserId) throws Exception;
	public Double getAmount(String sendersAccountId);
}
