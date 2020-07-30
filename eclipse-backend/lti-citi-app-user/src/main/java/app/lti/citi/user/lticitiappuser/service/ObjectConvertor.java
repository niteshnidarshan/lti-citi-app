package app.lti.citi.user.lticitiappuser.service;

import app.lti.citi.user.lticitiappuser.document.UserDetail;
import app.lti.citi.user.lticitiappuser.dto.UserDetailDto;

public class ObjectConvertor {

	public UserDetailDto actualToDto(UserDetail user) {
		return new UserDetailDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getMobile(), user.getSocialSecurityNumber(), user.getAssociatedAccounts(),user.getUserType(), user.getUserStatus(), user.getProfileCreationTimeStamp(), user.getProfileLastModifiedTimeStamp());
	}
	
	public UserDetail dtoToActual(UserDetailDto user) {
		return new UserDetail(user.getUserId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getEmail(), user.getMobile(), user.getSocialSecurityNumber(), user.getAssociatedAccounts(),user.getUserType(), user.getUserStatus(), user.getProfileCreationTimeStamp(), user.getProfileLastModifiedTimeStamp());
	}
}
