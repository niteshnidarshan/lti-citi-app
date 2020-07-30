package app.lti.citi.ag.lticitiappapigateway.feignproxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import app.lti.citi.ag.lticitiappapigateway.dto.UserDetailDto;

@FeignClient(name = "lti-citi-app-api-gateway")
@RibbonClient(name = "lti-citi-app-user")
public interface UserAuthFeignProxy {

	@PostMapping("/lti-citi-app-user/api/user/register")
	public ResponseEntity<UserDetailDto> register(@RequestBody UserDetailDto userDetailDto);
	 
	@GetMapping("/lti-citi-app-user/api/user/get-by-mobile/{mobile}")
	public ResponseEntity<UserDetailDto> getUserByMobile(@PathVariable("mobile") String mobile);
	
}
