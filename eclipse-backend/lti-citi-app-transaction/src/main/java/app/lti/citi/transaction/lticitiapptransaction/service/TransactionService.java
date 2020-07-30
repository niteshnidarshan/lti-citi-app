package app.lti.citi.transaction.lticitiapptransaction.service;

import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;

public interface TransactionService {

	public TransactionDetailDto transferAmount(TransactionDetailDto transferDetailDto) throws Exception;
}
