package app.lti.citi.transaction.lticitiapptransaction.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionExceptionDto {

	private String message;
	private Integer errorCode;
	private long timestamp;
	
}
