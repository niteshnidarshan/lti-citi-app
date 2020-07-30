package app.lti.citi.account.lticitiappaccount.dto;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDetailDto {

	private String accountId;
	private AccountType accountType;
	private Double amount;
	private String associatedUserId;
	private AccountStatus accountStatus;
	private Date accountCreationTimeStamp;
	private Date accountLastModifiedTimeStamp;
	
}
