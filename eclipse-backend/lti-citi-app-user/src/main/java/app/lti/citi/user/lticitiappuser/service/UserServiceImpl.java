package app.lti.citi.user.lticitiappuser.service;

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
	
	private ObjectConvertor convertor;

	@Override
	public UserDetailDto login(LoginDto loginDto) {
		UserDetailDto dto = null;
		
		if(loginDto != null && loginDto.getMobile() != null) {
			UserDetail user = this.repository.findByMobileAndUserStatus(loginDto.getMobile(), Status.ACTIVE.toString());
			if(user != null) {
				dto = this.convertor.actualToDto(user);
				dto.setSocialSecurityNumber(null);
			}
		}
		
		return dto;
	}

	@Override
	public UserDetailDto register(UserDetailDto userDetailDto) {
		UserDetailDto dto = null;
		
		if(userDetailDto != null) {
			userDetailDto.setUserId(null);
			userDetailDto.setUserType(UserType.GENERAL);
			userDetailDto.setUserStatus(Status.ACTIVE);
			UserDetail user = this.repository.save(this.convertor.dtoToActual(userDetailDto));
			if(user != null) {
				dto = this.convertor.actualToDto(user);
				dto.setSocialSecurityNumber(null);
			}
		}
		
		return dto;
	}

	@Override
	public UserDetailDto editProfile(UserDetailDto userDetailDto) {
		UserDetailDto dto = null;
		
		if(userDetailDto != null && userDetailDto.getUserId() != null) {
			UserDetail user = this.repository.findById(userDetailDto.getUserId()).orElse(null);
			if(user != null) {
				user.setFirstName(userDetailDto.getFirstName());
				user.setLastName(userDetailDto.getLastName());
				user.setEmail(userDetailDto.getEmail());
				user.setMobile(userDetailDto.getMobile());
				user = this.repository.save(user);
				if(user != null) {
					dto = this.convertor.actualToDto(user);
				}
			}
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
			UserDetail user = this.repository.findByMobileAndUserStatus(mobile, Status.ACTIVE.toString());
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
		UserDetail user = this.repository.findByMobileAndUserStatus(mobile, Status.ACTIVE.toString());
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
			UserDetail user = this.repository.findByMobileAndUserStatus(mobile, Status.ACTIVE.toString());
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
