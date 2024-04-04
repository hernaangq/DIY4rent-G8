package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Cliente;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Repository.ClienteRepository;
import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Enums.TipoClienteEnum;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;




@RestController
@CrossOrigin
public class ClienteController {

    private final ClienteRepository clienteRepository;
    private final PropietarioRepository propietarioRepository;

    public ClienteController(ClienteRepository clienteRepository, PropietarioRepository propietarioRepository) {
        this.clienteRepository = clienteRepository;
        this.propietarioRepository = propietarioRepository;
    }

    // Primero, los de acceso permitido a clientes normales y corrientes

    @GetMapping("/clientes/{id}")
	ResponseEntity<Cliente> getCliente(@PathVariable Integer id) {
	// return (List<Cliente>) clienteRepository.findBynombre("hernan");
	return clienteRepository.findById(id).map(c -> ResponseEntity.ok().body(c))
            .orElse(new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND));

	}

    // Para que el cliente pueda actualizar sus datos
    @PutMapping("/clientes/{id}")
	public ResponseEntity<Cliente> actualizarCliente (@RequestBody Cliente cliente, @PathVariable Integer id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
  
        return clienteRepository.findById(id).map(c -> {
        
        cliente.setNombre(cliente.getNombre());
        cliente.setApellidos(cliente.getApellidos());
        cliente.setEmail(cliente.getEmail());
        cliente.setLatitud(cliente.getLatitud());
        cliente.setLongitud(cliente.getLongitud());
        cliente.setIban(cliente.getIban());
        clienteRepository.save(cliente);
        return ResponseEntity.ok().body(cliente);
        })
        .orElse(new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND));
      }

      @PostMapping("/clientes")
      ResponseEntity<Cliente> añadirCliente(@RequestBody Cliente newCliente) throws URISyntaxException {
      Cliente result = clienteRepository.save(newCliente);
      return ResponseEntity.created(new URI("/clientes/" + result.getNombre() + result.getApellidos())).body(result);
      }

      @PostMapping("/propietarios")
      ResponseEntity<Propietario> añadirPropietario(@RequestBody Cliente newCliente) throws URISyntaxException {
      Propietario propietario = new Propietario();
      propietario.setId(newCliente.getId());
      TipoClienteEnum tipo = newCliente.getTipo();
      if (tipo.equals(TipoClienteEnum.PROPIETARIO)){
        propietario.setId(newCliente.getId());
        Propietario result = propietarioRepository.save(propietario);
        return ResponseEntity.created(new URI("/propietarios/" + propietario.getId())).body(result);
      } else {
        // En caso de que el tipo de cliente no sea "PROPIETARIO", puedes devolver un ResponseEntity con un estado de error
        return ResponseEntity.badRequest().build();
    }
      }


      // Para admins
      @GetMapping("/clientes")
      List<Cliente> getClientes() {
      // return (List<Cliente>) clienteRepository.findBynombre("hernan");
      return clienteRepository.findAll();
  
      }
}