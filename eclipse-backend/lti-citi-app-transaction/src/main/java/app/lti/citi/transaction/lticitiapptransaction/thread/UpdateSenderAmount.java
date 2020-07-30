package app.lti.citi.transaction.lticitiapptransaction.thread;

import java.util.concurrent.Callable;

import app.lti.citi.transaction.lticitiapptransaction.dto.AccountDetailDto;
import app.lti.citi.transaction.lticitiapptransaction.feignproxy.AccountFeignProxy;

public class UpdateSenderAmount implements Callable<AccountDetailDto>{

	private String accountId;
	private double amountToBeUpdated;
	private AccountFeignProxy proxy;
	
	public UpdateSenderAmount(String accountId, double amountToBeUpdated, AccountFeignProxy proxy) {
		this.accountId = accountId;
		this.amountToBeUpdated = amountToBeUpdated;
		this.proxy = proxy;
	}
	
	@Override
	public AccountDetailDto call() throws Exception {
		try {
			return this.proxy.updateAccountAmount(accountId, amountToBeUpdated).getBody();
		}catch(Exception ex) {
			throw ex;
		}
	}

}
