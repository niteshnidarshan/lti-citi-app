package app.lti.citi.ag.lticitiappapigateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
public class JwtResponse {

	private final String jwttoken;
	
}
