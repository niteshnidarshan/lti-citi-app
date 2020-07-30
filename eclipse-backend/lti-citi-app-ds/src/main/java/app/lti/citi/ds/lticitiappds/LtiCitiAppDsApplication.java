package app.lti.citi.ds.lticitiappds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LtiCitiAppDsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LtiCitiAppDsApplication.class, args);
	}

}
