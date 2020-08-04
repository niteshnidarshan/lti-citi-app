package app.lti.citi.user.lticitiappuser.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.lti.citi.user.lticitiappuser.dto.LoginDto;
import app.lti.citi.user.lticitiappuser.dto.UserDetailDto;
import app.lti.citi.user.lticitiappuser.dto.UserExceptionDto;
import app.lti.citi.user.lticitiappuser.exception.CommonException;
import app.lti.citi.user.lticitiappuser.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService service;
	
	@GetMapping("/message")
	public ResponseEntity<String> getMessage(){
		ResponseEntity<String> response = new ResponseEntity<String>("User warmed up", HttpStatus.OK);
		
		return response; 
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDetailDto> getUserLogin(@RequestBody LoginDto loginDto){
		UserDetailDto dto = null;
		
		if(loginDto == null)
			throw new CommonException("Invalid login credentials!");
		
		try {
			dto = this.service.login(loginDto);
		} catch (Exception e) {
			throw new CommonException(e.getMessage());
		}
		
		if(dto == null) {
			throw new CommonException("Wrong login request!");
		}
		
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(dto, HttpStatus.OK);
		
		return response; 
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDetailDto> register(@RequestBody UserDetailDto userDetailDto){
		UserDetailDto dto = null; 
		if(userDetailDto == null)
			throw new CommonException("Invalid input!");
		
		if(this.service.isMobileRegisterd(userDetailDto.getMobile())) {
			throw new CommonException("Mobile already registerd!");
		}
		
		try { 
			dto = this.service.register(userDetailDto); 
		} catch (Exception e) {
			throw new CommonException(e.getMessage());
		}
		
		if(dto == null) {
			throw new CommonException("Something wrong with system!");
		}
		
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(dto, HttpStatus.OK);
		
		return response; 
		
	}
	
	@PutMapping("/modify")
	public ResponseEntity<UserDetailDto> modify(@RequestBody UserDetailDto userDetailDto){
		UserDetailDto dto = null;
		
		if(userDetailDto == null)
			throw new CommonException("Invalid input!");
		
		try {
			dto = this.service.editProfile(userDetailDto);
		} catch (Exception e) {
			throw new CommonException(e.getMessage());
		}
		
		if(dto == null) {
			throw new CommonException("Something wrong with system!");
		}
		
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(dto, HttpStatus.OK);
		
		return response; 
		
	}
	
	@GetMapping("/get-by-userId/{userId}")
	public ResponseEntity<UserDetailDto> getUserByUserId(@PathVariable("userId") String userId){
		UserDetailDto dto = null;
		
		if(userId == null)
			throw new CommonException("Invalid input!");
		
		 
		dto = this.service.getUserByUserId(userId);
		
		if(dto == null) {
			throw new CommonException("No such user registered!");
		}
		
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(dto, HttpStatus.OK);
		
		return response; 
		
	}
	
	@GetMapping("/get-by-mobile/{mobile}")
	public ResponseEntity<UserDetailDto> getUserByMobile(@PathVariable("mobile") String mobile){
		UserDetailDto dto = null;
		
		if(mobile == null)
			throw new CommonException("Invalid input!");
		
		 
		dto = this.service.getUserByMobile(mobile);
		
		if(dto == null) {
			throw new CommonException("No such mobile registered!");
		}
		
		ResponseEntity<UserDetailDto> response = new ResponseEntity<UserDetailDto>(dto, HttpStatus.OK);
		
		return response; 
		
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<UserDetailDto>> getAllUser(){ 
		 
		List<UserDetailDto> dtoList = null;
		
		dtoList = this.service.getAllUserDetail();
		
		if(dtoList == null) {
			throw new CommonException("No user available!");
		}
		
		ResponseEntity<List<UserDetailDto>> response = new ResponseEntity<List<UserDetailDto>>(dtoList, HttpStatus.OK);
		
		return response; 
		
	}
	
	@DeleteMapping("/delete/{mobile}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("mobile") String mobile){
		Boolean status = false;
		
		if(mobile == null)
			throw new CommonException("Invalid input!");
		
		 
		status = this.service.deleteUser(mobile);
		
		if(status == false) {
			throw new CommonException("No such user available!");
		}
		
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(status, HttpStatus.OK);
		
		return response; 
	}
	
	@ExceptionHandler(CommonException.class)// It is used to handle exception in rest controller - if not defined, then in return response a trace element will also be attached with exception
	public ResponseEntity<UserExceptionDto> commonExceptionHandler(CommonException ex){
		
		logger.error(ex.getMessage());
		
		UserExceptionDto exceptionDto = new UserExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<UserExceptionDto> response = new ResponseEntity<UserExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
}
