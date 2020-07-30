package app.lti.citi.transaction.lticitiapptransaction.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetailDto {

	private String transactionId;
	private TransactionType transactionType;
	private String senderAccountId;
	private String receiversAccountId;
	private Double amount; 
	private String associatedUserId;
	private TransactionStatus transactionStatus;
	private Date transactionTimeStamp;
	
}
