package app.lti.citi.account.lticitiappaccount.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import app.lti.citi.account.lticitiappaccount.dto.AccountStatus;
import app.lti.citi.account.lticitiappaccount.dto.AccountType;
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
@Document
public class AccountDetail {

	@Id
	private String accountId;
	private AccountType accountType;
	private Double amount;
	private String associatedUserId;
	private AccountStatus accountStatus;
	private Date accountCreationTimeStamp;
	private Date accountLastModifiedTimeStamp;
	
}
