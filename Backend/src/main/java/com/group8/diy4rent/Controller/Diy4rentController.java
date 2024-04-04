package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Cliente;
import com.group8.diy4rent.Repository.ClienteRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@CrossOrigin
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class Diy4rentController {

	// @Autowired(required = false)
	private final ClienteRepository clienteRepository;

	public Diy4rentController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

		//   nombre = "pablwewo";
	//   apellidos = "bote";
	//   email = "pablo@email.com";
	//   coordenadas = "9871";
	//   iban = "ES2345";
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewClient (@RequestBody Cliente clienterequest) {
	  // @ResponseBody means the returned String is the response, not a view name
	  // @RequestParam means it is a parameter from the GET or POST request


	  Cliente n = new Cliente();
	  n.setNombre(clienterequest.getNombre());
	  n.setApellidos(clienterequest.getApellidos());
	  n.setEmail(clienterequest.getEmail());
	  n.setLatitud(clienterequest.getLatitud());
	  n.setLongitud(clienterequest.getLongitud());
	  n.setIban(clienterequest.getIban());
	  clienteRepository.save(n);
	  return "Saved";
	}

	    @PostMapping("/clientes")
    	ResponseEntity<Cliente> createCliente(@RequestBody Cliente newCliente) throws URISyntaxException {
        Cliente result = clienteRepository.save(newCliente);
        return ResponseEntity.created(new URI("/clientes/" + result.getNombre() + result.getApellidos())).body(result);

    }
	@GetMapping
	List<Cliente> getClientes() {
	// return (List<Cliente>) clienteRepository.findBynombre("hernan");
	return (List<Cliente>) clienteRepository.findAll();

	} 

}