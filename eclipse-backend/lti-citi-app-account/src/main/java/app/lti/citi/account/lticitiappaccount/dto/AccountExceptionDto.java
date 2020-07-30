package app.lti.citi.account.lticitiappaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountExceptionDto {

	private String message;
	private Integer errorCode;
	private long timestamp;
	
}
