package app.lti.citi.ag.lticitiappapigateway.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.lti.citi.ag.lticitiappapigateway.dto.UserDetailDto;
import app.lti.citi.ag.lticitiappapigateway.feignproxy.UserAuthFeignProxy; 

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserAuthFeignProxy proxy;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
		 
		UserDetailDto user = null;
		try {
			user = this.proxy.getUserByMobile(mobile).getBody();
		}catch(Exception ex) {
			throw new UsernameNotFoundException("User not found with mobile: " + mobile);
		}
		if (user == null) {
			throw new UsernameNotFoundException("User not found with mobile: " + mobile);
		}
		 
		return new org.springframework.security.core.userdetails.User(user.getMobile(), user.getSocialSecurityNumber(), new ArrayList<>());
		
	} 
	
	public UserDetailDto save(UserDetailDto dto) throws Exception {
		UserDetailDto userDetailDto = null; 
		try {
		dto.setSocialSecurityNumber(bcryptEncoder.encode(dto.getSocialSecurityNumber())); //setting encrypted password to dto 
		
		userDetailDto = this.proxy.register(dto).getBody(); //Register user request with feign proxy with encrypted password value.
		}catch(Exception ex) {
			System.out.println("error is = "+ex);
			throw ex;
		}  
		return userDetailDto; 
	}

}
