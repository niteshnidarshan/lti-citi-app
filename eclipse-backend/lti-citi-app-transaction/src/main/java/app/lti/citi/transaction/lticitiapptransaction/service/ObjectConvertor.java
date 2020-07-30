package app.lti.citi.transaction.lticitiapptransaction.service;

import app.lti.citi.transaction.lticitiapptransaction.document.TransactionDetail;
import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;

public class ObjectConvertor {

	public TransactionDetailDto actualToDto(TransactionDetail obj) {
		return new TransactionDetailDto(obj.getTransactionId(), obj.getTransactionType(), obj.getSenderAccountId(), obj.getReceiversAccountId(), obj.getAmount(), obj.getAssociatedUserId(), obj.getTransactionStatus(), obj.getTransactionTimeStamp());
	}
	
	public TransactionDetail dtoToActual(TransactionDetailDto obj) {
		return new TransactionDetail(obj.getTransactionId(), obj.getTransactionType(), obj.getSenderAccountId(), obj.getReceiversAccountId(), obj.getAmount(), obj.getAssociatedUserId(), obj.getTransactionStatus(), obj.getTransactionTimeStamp());
	}
}
