package app.lti.citi.user.lticitiappuser.feignproxy;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import app.lti.citi.user.lticitiappuser.dto.AccountDetailDto;
 

@FeignClient(name = "lti-citi-app-api-gateway", url = "https://lti-citi-ag.herokuapp.com/")
@RibbonClient(name = "lti-citi-app-account")
public interface AccountProxy {

	@GetMapping("/lti-citi-app-account/api/account/getAll/{customerId}")
	public ResponseEntity<List<AccountDetailDto>> getAllAccountByCustomerId(@PathVariable("customerId") String customerId);
	
}
