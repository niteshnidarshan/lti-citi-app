package app.lti.citi.account.lticitiappaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LtiCitiAppAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtiCitiAppAccountApplication.class, args);
	}

}
