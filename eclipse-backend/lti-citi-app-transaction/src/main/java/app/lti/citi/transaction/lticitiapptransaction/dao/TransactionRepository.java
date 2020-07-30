package app.lti.citi.transaction.lticitiapptransaction.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.lti.citi.transaction.lticitiapptransaction.document.TransactionDetail;
import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;

public interface TransactionRepository extends MongoRepository<TransactionDetail, String> {

	public List<TransactionDetail> findAllByAssociatedUserId(String associatedUserId);
	
}
