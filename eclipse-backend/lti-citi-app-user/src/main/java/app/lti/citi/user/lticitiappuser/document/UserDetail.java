package app.lti.citi.user.lticitiappuser.document;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import app.lti.citi.user.lticitiappuser.dto.AccountDetailDto;
import app.lti.citi.user.lticitiappuser.dto.Status;
import app.lti.citi.user.lticitiappuser.dto.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetail {

	@Id
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
