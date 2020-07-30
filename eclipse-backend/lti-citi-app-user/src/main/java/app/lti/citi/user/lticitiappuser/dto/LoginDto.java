package app.lti.citi.user.lticitiappuser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	private String mobile; 
	private String socialSecurityNumber;
	
}
