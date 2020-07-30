package app.lti.citi.ag.lticitiappapigateway.dto;

import java.util.Date; 
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
	private String accountType;
	private Double amount;
	private String associatedUserId;
	private Date accountCreationTimeStamp;
	private Date accountLastModifiedTimeStamp;
	
}
