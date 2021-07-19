package ar.com.plug.examen.domain.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EchoController {


	@GetMapping("/")
	public ResponseEntity<String> getSaludo()  {
		return new ResponseEntity<String>("Bienvenido", HttpStatus.OK);
	}
}
