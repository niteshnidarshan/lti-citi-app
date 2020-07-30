package app.lti.citi.user.lticitiappuser.service;

import java.util.List;

import app.lti.citi.user.lticitiappuser.dto.LoginDto;
import app.lti.citi.user.lticitiappuser.dto.UserDetailDto;

public interface UserService {
	
	public UserDetailDto login(LoginDto loginDto) throws Exception;
	public UserDetailDto register(UserDetailDto userDetailDto)  throws Exception;
	public UserDetailDto editProfile(UserDetailDto userDetailDto) throws Exception;
	public UserDetailDto getUserByUserId(String userId);
	public UserDetailDto getUserByMobile(String mobile);
	public boolean deleteUser(String mobile);
	public List<UserDetailDto> getAllUserDetail(); 
	public boolean isMobileRegisterd(String mobile);
	
}
