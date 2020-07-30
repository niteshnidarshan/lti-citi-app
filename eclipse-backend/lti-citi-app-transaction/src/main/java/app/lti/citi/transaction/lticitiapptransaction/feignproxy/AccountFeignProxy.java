package app.lti.citi.transaction.lticitiapptransaction.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.lti.citi.transaction.lticitiapptransaction.dto.AccountDetailDto;

@FeignClient(name = "lti-citi-app-account") 
//@FeignClient(name = "api-gateway")
@RibbonClient(name = "lti-citi-app-account")
public interface AccountFeignProxy {
	
	//@PostMapping("/lti-citi-app-account/api/account/update-amount/{accountId}/{amount}")
	@PostMapping("/api/account/update-amount/{accountId}/{amount}")
	public ResponseEntity<AccountDetailDto> updateAccountAmount(@PathVariable("accountId") String accountId, @PathVariable("amount") Double amount);

	//@GetMapping("/lti-citi-app-account/api/account/get-amount/{accountId}")
	@GetMapping("/api/account/get-amount/{accountId}")
	public ResponseEntity<Double> getAmount(@PathVariable("accountId") String accountId);
	
}
