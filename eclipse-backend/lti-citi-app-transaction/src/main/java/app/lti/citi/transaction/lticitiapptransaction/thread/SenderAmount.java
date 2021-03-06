package app.lti.citi.transaction.lticitiapptransaction.thread;

import java.util.concurrent.Callable;

import app.lti.citi.transaction.lticitiapptransaction.feignproxy.AccountFeignProxy;

public class SenderAmount implements Callable<Double>{
	/**
	 * To fetch receiver's total amount
	 */
	private String accountId;
	private AccountFeignProxy proxy;
	
	public SenderAmount(String accountId, AccountFeignProxy proxy) {
		this.accountId = accountId;
		this.proxy = proxy;
	}

	@Override
	public Double call() throws Exception {
		try {
		 return this.proxy.getAmount(accountId).getBody();
		}catch(Exception e) {
			throw e;
		}
	}

}
