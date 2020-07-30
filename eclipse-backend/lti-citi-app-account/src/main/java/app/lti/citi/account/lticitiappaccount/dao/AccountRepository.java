package app.lti.citi.account.lticitiappaccount.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.lti.citi.account.lticitiappaccount.document.AccountDetail;

@Repository
public interface AccountRepository extends MongoRepository<AccountDetail, String>{

	public List<AccountDetail> findAllByAssociatedUserId(String associatedUserId);
	
}
