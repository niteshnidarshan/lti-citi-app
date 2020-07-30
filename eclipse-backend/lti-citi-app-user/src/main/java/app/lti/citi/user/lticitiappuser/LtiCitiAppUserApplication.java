package app.lti.citi.user.lticitiappuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LtiCitiAppUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtiCitiAppUserApplication.class, args);
	}

}
