package app.lti.citi.user.lticitiappuser.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lti.citi.user.lticitiappuser.dao.UserRepository;
import app.lti.citi.user.lticitiappuser.document.UserDetail;
import app.lti.citi.user.lticitiappuser.dto.LoginDto;
import app.lti.citi.user.lticitiappuser.dto.Status;
import app.lti.citi.user.lticitiappuser.dto.UserDetailDto;
import app.lti.citi.user.lticitiappuser.dto.UserType;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ObjectConvertor convertor;

	@Override
	public UserDetailDto login(LoginDto loginDto) throws Exception {
		UserDetailDto dto = null;
		
		if(loginDto != null && loginDto.getMobile() != null) {
			try {
				UserDetail user = this.repository.findByMobileAndSocialSecurityNumberAndUserStatus(loginDto.getMobile(), loginDto.getSocialSecurityNumber(), Status.ACTIVE);
				if(user != null) {
					dto = this.convertor.actualToDto(user);
					dto.setSocialSecurityNumber(null);
				}
			}catch(Exception ex) {
				throw ex;
			}
		}
		
		return dto;
	}

	@Override
	public UserDetailDto register(UserDetailDto userDetailDto) throws Exception {
		UserDetailDto dto = null;
		
		if(userDetailDto != null) {
			try { 
				userDetailDto.setUserId(null);
				userDetailDto.setUserType(UserType.GENERAL);
				userDetailDto.setUserStatus(Status.ACTIVE);
				userDetailDto.setProfileCreationTimeStamp(new Date(System.currentTimeMillis())); 
				
				UserDetail user = this.convertor.dtoToActual(userDetailDto);
				
				user = this.repository.save(user); 
				if(user != null) {
					dto = this.convertor.actualToDto(user);
					dto.setSocialSecurityNumber(null);
				}
			}catch(Exception ex) {
				throw ex;
			}
		}
		
		return dto;
	}

	@Override
	public UserDetailDto editProfile(UserDetailDto userDetailDto) throws Exception {
		UserDetailDto dto = null;
		
		try {
			if(userDetailDto != null && userDetailDto.getUserId() != null) {
				UserDetail user = this.repository.findById(userDetailDto.getUserId()).orElse(null);
				if(user != null) {
					user.setFirstName(userDetailDto.getFirstName());
					user.setLastName(userDetailDto.getLastName());
					user.setEmail(userDetailDto.getEmail());
					user.setMobile(userDetailDto.getMobile());
					user.setProfileLastModifiedTimeStamp(new Date(System.currentTimeMillis()));
					user = this.repository.save(user);
					if(user != null) {
						dto = this.convertor.actualToDto(user);
					}
				}
			}
		}catch(Exception ex) {
			throw ex;
		}
		
		return dto;
	}

	@Override
	public UserDetailDto getUserByUserId(String userId) {
		UserDetailDto dto = null;
		
		if(userId != null) {
			UserDetail user = this.repository.findById(userId).orElse(null);
			if(user != null) {
				dto = this.convertor.actualToDto(user);
			}				
		}
		
		return dto;
	}

	@Override
	public UserDetailDto getUserByMobile(String mobile) {
		UserDetailDto dto = null;
		
		if(mobile != null) {
			UserDetail user = this.repository.findByMobileAndUserStatus(mobile, Status.ACTIVE);
			if(user != null) {
				dto = this.convertor.actualToDto(user);
			}				
		}
		
		return dto;
	}

	@Override
	public List<UserDetailDto> getAllUserDetail() {
		List<UserDetailDto> userList = new LinkedList<UserDetailDto>();
		
		List<UserDetail> userDetailList = this.repository.findAll();
		
		userDetailList.forEach(
				(user)->{
					if(user != null) {
						UserDetailDto dto = this.convertor.actualToDto(user);
						userList.add(dto);
					}
				}
			);
		
		return userList;
	}
	
	public boolean isMobileRegisterd(String mobile) {
		UserDetail user = this.repository.findByMobileAndUserStatus(mobile, Status.ACTIVE);
		if(user == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean deleteUser(String mobile) {
		/**
		 * If user is deleted, make user as INACTIVE
		 */
		boolean deleteStatus = false;
		if(mobile != null) {
			UserDetail user = this.repository.findByMobileAndUserStatus(mobile, Status.ACTIVE);
			if(user != null) {
				user.setUserStatus(Status.INACTIVE);
				user = this.repository.save(user);
				if(user != null) {
					deleteStatus = true;
				}
			}
		}
		return deleteStatus;
	}
 
}
