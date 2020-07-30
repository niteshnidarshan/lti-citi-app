package app.lti.citi.account.lticitiappaccount.service;

import app.lti.citi.account.lticitiappaccount.dto.AccountDetailDto;

public interface AccountService {

	public AccountDetailDto createAccount(AccountDetailDto accountDetailDto);
	public AccountDetailDto modifyAccount(AccountDetailDto accountDetailDto);
	public boolean deleteAccount(String accountId);
	public AccountDetailDto updateAccountAmount(String accountId, Double amount) throws Exception;
	public Double getAmount(String accountId) throws Exception;
}
