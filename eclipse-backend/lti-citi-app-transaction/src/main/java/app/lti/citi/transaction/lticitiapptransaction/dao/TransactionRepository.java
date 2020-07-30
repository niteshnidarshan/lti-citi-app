package app.lti.citi.transaction.lticitiapptransaction.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.lti.citi.transaction.lticitiapptransaction.document.TransactionDetail;

public interface TransactionRepository extends MongoRepository<TransactionDetail, String> {

}
