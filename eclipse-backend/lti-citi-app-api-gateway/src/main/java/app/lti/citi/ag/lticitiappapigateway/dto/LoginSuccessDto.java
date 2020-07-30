package app.lti.citi.ag.lticitiappapigateway.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class LoginSuccessDto {
	
	private String userId;
	private String firstName;
	private String lastName; 
	private String email;  
	private String mobile;
	private UserType userType;
	private String token; 

}
