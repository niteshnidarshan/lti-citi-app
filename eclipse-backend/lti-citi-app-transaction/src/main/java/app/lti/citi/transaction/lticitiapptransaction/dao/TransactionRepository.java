package app.lti.citi.transaction.lticitiapptransaction.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.lti.citi.transaction.lticitiapptransaction.document.TransactionDetail;
import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionDetailDto;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionDetail, String> {

	public List<TransactionDetail> findAllByAssociatedUserId(String associatedUserId);
	
}
