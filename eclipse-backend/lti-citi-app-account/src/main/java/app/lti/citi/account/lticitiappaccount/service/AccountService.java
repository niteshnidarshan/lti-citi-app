package app.lti.citi.account.lticitiappaccount.service;

import java.util.List;

import app.lti.citi.account.lticitiappaccount.dto.AccountDetailDto;

public interface AccountService {

	public AccountDetailDto createAccount(AccountDetailDto accountDetailDto);
	public AccountDetailDto modifyAccount(AccountDetailDto accountDetailDto);
	public AccountDetailDto getAccountDetail(String accountId);
	public List<AccountDetailDto> getAccountByCustomerId(String associatedUserId);
	public List<AccountDetailDto> getAllAccountDetail();
	public boolean deleteAccount(String accountId);
	public AccountDetailDto updateAccountAmount(String accountId, Double amount) throws Exception;
	public Double getAmount(String accountId) throws Exception;
}
