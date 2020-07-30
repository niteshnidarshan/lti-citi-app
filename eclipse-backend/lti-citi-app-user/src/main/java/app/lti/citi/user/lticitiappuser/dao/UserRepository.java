package app.lti.citi.user.lticitiappuser.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import app.lti.citi.user.lticitiappuser.document.UserDetail;
import app.lti.citi.user.lticitiappuser.dto.Status;

@Repository
public interface UserRepository extends MongoRepository<UserDetail, String> {

	public UserDetail findByMobileAndUserStatus(String mobile, Status userStatus);
	public UserDetail findByMobileAndSocialSecurityNumberAndUserStatus(String mobile, String socialSecurityNumber, Status userStatus);
	
}
