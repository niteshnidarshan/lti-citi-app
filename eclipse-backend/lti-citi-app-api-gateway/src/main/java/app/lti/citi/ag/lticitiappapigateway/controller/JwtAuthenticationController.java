package app.lti.citi.ag.lticitiappapigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.lti.citi.ag.lticitiappapigateway.config.JwtTokenUtil;
import app.lti.citi.ag.lticitiappapigateway.dto.JwtRequest;
import app.lti.citi.ag.lticitiappapigateway.dto.LoginSuccessDto;
import app.lti.citi.ag.lticitiappapigateway.dto.UserDetailDto;
import app.lti.citi.ag.lticitiappapigateway.dto.UserExceptionDto;
import app.lti.citi.ag.lticitiappapigateway.exception.CommonException;
import app.lti.citi.ag.lticitiappapigateway.feignproxy.UserAuthFeignProxy;
import app.lti.citi.ag.lticitiappapigateway.service.JwtUserDetailsService; 

@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Autowired
	private UserAuthFeignProxy proxy;
	
	@GetMapping("/message")
	public ResponseEntity<String> getMessage(){
		ResponseEntity<String> response = new ResponseEntity<String>("Api-Gateway warmed up", HttpStatus.OK);
		
		return response; 
	}
	 
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<LoginSuccessDto> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		 
		//Here userName is mobile
		
		
		UserDetailDto dto = null;
		
		LoginSuccessDto success = null;
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		  
		if(userDetails != null) {
			//once user is valid then need to return token with userdetails  
			dto = this.proxy.getUserByMobile(authenticationRequest.getUsername()).getBody();
			 
			final String token = jwtTokenUtil.generateToken(userDetails);
			
			success = new LoginSuccessDto();
			
			success.setUserId(dto.getUserId());
			success.setFirstName(dto.getFirstName());
			success.setLastName(dto.getLastName());
			success.setEmail(dto.getEmail());
			success.setMobile(dto.getMobile());
			success.setUserType(dto.getUserType());
			success.setToken(token);
			
		} 
		
		ResponseEntity<LoginSuccessDto> response = new ResponseEntity<LoginSuccessDto>(success, HttpStatus.OK);
		return response; 
	}
	
	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public ResponseEntity<UserDetailDto> saveUser(@RequestBody UserDetailDto user) throws Exception {
		 
		UserDetailDto returnDto = null;
		
		try {
			returnDto = userDetailsService.save(user);
		}catch(Exception e) { 
			throw new CommonException("Mobile already in use! Try login with valid Social Security Number.");
		} 
		
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(returnDto, HttpStatus.OK);
		return response;
		
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new CommonException("USER_DISABLED");
		} catch (BadCredentialsException e) {
			throw new CommonException("Invalid credential. Either Mobile or Social Security Number is wrong!");
		}
	}
	
	@ExceptionHandler(CommonException.class)// It is used to handle exception in rest controller - if not defined, then in return response a trace element will also be attached with exception
	public ResponseEntity<UserExceptionDto> commonExceptionHandler(CommonException ex){ 
		UserExceptionDto exceptionDto = new UserExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = new ResponseEntity<UserExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}

}
