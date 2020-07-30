package app.lti.citi.transaction.lticitiapptransaction.thread;

import java.util.concurrent.Callable;

import app.lti.citi.transaction.lticitiapptransaction.feignproxy.AccountFeignProxy;

public class ReceiverAmount implements Callable<Double>{
	/**
	 * To fetch Receiver's total amount
	 */
	private String accountId;
	private AccountFeignProxy proxy;
	
	public ReceiverAmount(String accountId, AccountFeignProxy proxy) {
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
