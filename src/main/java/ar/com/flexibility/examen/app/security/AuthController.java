package ar.com.flexibility.examen.app.security;

import java.util.Collections;
import java.util.Map;
import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import ar.com.flexibility.examen.app.service.CustomerService;
import ar.com.flexibility.examen.domain.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;


	@PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getEmail(),
						loginDTO.getPassword()
				)
		);

		String token = jwtTokenUtil.generateToken(authentication.getName());

		Map<String, String> map = Collections.singletonMap("token", token);

		return new ResponseEntity<>(map, HttpStatus.OK);
	}


	/**
	 * @param customer
	 * @return
	 * @throws EntityExistsException Sign up. Creates a new customer.
	 */
	@PostMapping(path = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) throws EntityExistsException {
		Customer createdCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}

}
