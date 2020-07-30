package app.lti.citi.user.lticitiappuser.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.lti.citi.user.lticitiappuser.document.UserDetail;

@Repository
public interface UserRepository extends MongoRepository<UserDetail, String> {

	public UserDetail findByMobileAndUserStatus(String mobile, String userStatus);
	
}
