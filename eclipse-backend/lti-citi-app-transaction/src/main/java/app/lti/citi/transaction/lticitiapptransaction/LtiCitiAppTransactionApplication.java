package app.lti.citi.transaction.lticitiapptransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("app.lti.citi.transaction.lticitiapptransaction.feignproxy")
public class LtiCitiAppTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtiCitiAppTransactionApplication.class, args);
	}

}
