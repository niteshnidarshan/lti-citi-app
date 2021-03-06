package app.lti.citi.account.lticitiappaccount.service;

import org.springframework.stereotype.Component;

import app.lti.citi.account.lticitiappaccount.document.AccountDetail;
import app.lti.citi.account.lticitiappaccount.dto.AccountDetailDto;

@Component
public class ObjectConvertor {

	public AccountDetailDto actualToDto(AccountDetail ac) {
		return new AccountDetailDto(ac.getAccountId(), ac.getAccountType(), ac.getAmount(), ac.getAssociatedUserId(),ac.getAccountStatus(), ac.getAccountCreationTimeStamp(), ac.getAccountLastModifiedTimeStamp());
	}
	public AccountDetail DtoToActual(AccountDetailDto ac) {
		return new AccountDetail(ac.getAccountId(), ac.getAccountType(), ac.getAmount(), ac.getAssociatedUserId(), ac.getAccountStatus(), ac.getAccountCreationTimeStamp(), ac.getAccountLastModifiedTimeStamp());
	}
}
