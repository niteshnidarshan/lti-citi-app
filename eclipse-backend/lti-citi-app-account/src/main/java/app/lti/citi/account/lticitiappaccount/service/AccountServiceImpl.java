package app.lti.citi.account.lticitiappaccount.service;
 
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lti.citi.account.lticitiappaccount.dao.AccountRepository;
import app.lti.citi.account.lticitiappaccount.document.AccountDetail;
import app.lti.citi.account.lticitiappaccount.dto.AccountDetailDto;
import app.lti.citi.account.lticitiappaccount.dto.AccountStatus;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository repository;
	
	private ObjectConvertor convertor;

	@Override
	public AccountDetailDto createAccount(AccountDetailDto accountDetailDto) {
		AccountDetailDto dto = null;
		if(accountDetailDto != null) {
			AccountDetail account = this.convertor.DtoToActual(accountDetailDto);
			account.setAccountCreationTimeStamp(new Date(System.currentTimeMillis()));
			account = this.repository.save(account);
			if(account != null) {
				dto = this.convertor.actualToDto(account);
			}
		}
		return dto;
	}

	@Override
	public AccountDetailDto modifyAccount(AccountDetailDto accountDetailDto) {
		/**
		 * Currently it updates Account type only
		 */
		AccountDetailDto dto = null;
		if(accountDetailDto != null) {
			AccountDetail account = this.repository.findById(accountDetailDto.getAccountId()).orElse(null);
			if(account != null) {
				account.setAccountType(accountDetailDto.getAccountType());
				account.setAccountLastModifiedTimeStamp(new Date(System.currentTimeMillis()));
				account = this.repository.save(account);
				if(account != null) {
					dto = this.convertor.actualToDto(account);
				}
			}
		}
		return dto;
	}

	@Override
	public boolean deleteAccount(String accountId) {
		boolean deleteStatus = false;
		
		if(accountId != null) {
			AccountDetail account = this.repository.findById(accountId).orElse(null);
			if(account != null) {
				account.setAccountStatus(AccountStatus.INACTIVE);
				account.setAccountLastModifiedTimeStamp(new Date(System.currentTimeMillis()));
				account = this.repository.save(account);
				if(account != null) {
					deleteStatus = true;
				}
			}
		}
		
		return deleteStatus;
	}

	@Override
	public AccountDetailDto updateAccountAmount(String accountId, Double amount) throws Exception{
		 
		AccountDetailDto dto = null;
		Lock lock = null;
		try {
			if(accountId != null) {
				lock = new ReentrantLock(true);
				AccountDetail account = this.repository.findById(accountId).orElse(null);
				if(account != null) {
					account.setAmount(amount);
					account.setAccountLastModifiedTimeStamp(new Date(System.currentTimeMillis()));
					account = this.repository.save(account);
					if(account != null) {
						dto = this.convertor.actualToDto(account);
					}
				}
			}
		}catch(Exception ex) { 
			throw ex;
		}
		finally {
			if(lock != null) {
				lock.unlock();
			}
		}
		
		return dto;
	}

	@Override
	public Double getAmount(String accountId) throws Exception{
		Double amount = 0.0d;
		Lock lock = null;
		try {
			if(accountId != null) {
				lock = new ReentrantLock(true);
				AccountDetail account = this.repository.findById(accountId).orElse(null);
				if(account != null) { 
					amount = account.getAmount();
				}
			}
		}catch(Exception ex) {
			throw ex;
		}
		finally {
			if(lock != null) {
				lock.unlock();
			}
		}
		return amount;
	}

}
