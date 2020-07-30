package app.lti.citi.user.lticitiappuser.dto;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString; 
 
@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class UserDetailDto {

	private String userId;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String mobile;
	private String socialSecurityNumber; //using as a password - encrypted 
	private List<AccountDetailDto> associatedAccounts;  //One user might have n number of accounts
	private UserType userType;
	private Status userStatus; //To maintain delete(On delete - INACTIVE)
	private Date profileCreationTimeStamp;
	private Date profileLastModifiedTimeStamp;
	
}
