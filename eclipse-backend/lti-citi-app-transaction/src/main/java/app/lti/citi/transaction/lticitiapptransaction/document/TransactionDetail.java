package app.lti.citi.transaction.lticitiapptransaction.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionStatus;
import app.lti.citi.transaction.lticitiapptransaction.dto.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor 
public class TransactionDetail {

	@Id
	private String transactionId;
	private TransactionType transactionType;
	private String senderAccountId;
	private String receiversAccountId;
	private Double amount;
	private String associatedUserId;
	private TransactionStatus transactionStatus; //Should be updated after transaction if no exception in contrller 
	private Date transactionTimeStamp;
	
}
