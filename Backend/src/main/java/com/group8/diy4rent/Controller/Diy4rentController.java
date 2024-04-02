package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Cliente;
import com.group8.diy4rent.Repository.ClienteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class Diy4rentController {

	@Autowired(required = false)
	private ClienteRepository clienteRepository;

	// public Diy4rentController(ClienteRepository clienteRepository) {
    //     this.clienteRepository = clienteRepository;
    // }

	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewClient (@RequestParam String nombre
		, @RequestParam String apellidos, @RequestParam String email, @RequestParam String coordenadas, @RequestParam String iban) {
	  // @ResponseBody means the returned String is the response, not a view name
	  // @RequestParam means it is a parameter from the GET or POST request
  
	  Cliente n = new Cliente();
	  n.setNombre(nombre);
	  n.setApellidos(apellidos);
	  n.setEmail(email);
	  n.setCoordenadas(coordenadas);
	  n.setIban(iban);
	  clienteRepository.save(n);
	  return "Saved";
	}

	@GetMapping("/all")
	public String getAllUsers() {
		return "hola";
	}

}