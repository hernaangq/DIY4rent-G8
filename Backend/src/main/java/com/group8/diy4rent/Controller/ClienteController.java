package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Usuario;

import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Repository.UsuarioRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    // private final ClienteRepository clienteRepository;
    private final PropietarioRepository propietarioRepository;
    private final UsuarioRepository usuarioRepository;

    public ClienteController(PropietarioRepository propietarioRepository, UsuarioRepository usuarioRepository) {
        // ClienteRepository clienteRepository, this.clienteRepository = clienteRepository;
        this.propietarioRepository = propietarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    // Primero, los de acceso permitido a clientes normales y corrientes

  @GetMapping("/propietarios/{id}")
	ResponseEntity<Propietario> getPropietario(@PathVariable Integer id) {
	// return (List<Cliente>) clienteRepository.findBynombre("hernan");
	return propietarioRepository.findById(id).map(c -> ResponseEntity.ok().body(c))
            .orElse(new ResponseEntity<Propietario>(HttpStatus.NOT_FOUND));
	}

  @GetMapping("/usuarios/{id}")
	ResponseEntity<Usuario> getUsuario(@PathVariable Integer id) {
	// return (List<Cliente>) clienteRepository.findBynombre("hernan");
	return usuarioRepository.findById(id).map(c -> ResponseEntity.ok().body(c))
            .orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
	}

    // Para que el cliente pueda actualizar sus datos
  @PutMapping("/propietarios/{id}")
	public ResponseEntity<Propietario> actualizarPropietario (@RequestBody Propietario cliente, @PathVariable Integer id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
  
        return propietarioRepository.findById(id).map(c -> {
        
        cliente.setNombre(cliente.getNombre());
        cliente.setApellidos(cliente.getApellidos());
        cliente.setEmail(cliente.getEmail());
        cliente.setLatitud(cliente.getLatitud());
        cliente.setLongitud(cliente.getLongitud());
        cliente.setIban(cliente.getIban());
        propietarioRepository.save(cliente);
        return ResponseEntity.ok().body(cliente);
        })
        .orElse(new ResponseEntity<Propietario>(HttpStatus.NOT_FOUND));
      }
  
  @PutMapping("/usuarios/{id}")
  public ResponseEntity<Usuario> actualizarUsuario (@RequestBody Usuario cliente, @PathVariable Integer id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
  
        return usuarioRepository.findById(id).map(c -> {
        
        cliente.setNombre(cliente.getNombre());
        cliente.setApellidos(cliente.getApellidos());
        cliente.setEmail(cliente.getEmail());
        cliente.setLatitud(cliente.getLatitud());
        cliente.setLongitud(cliente.getLongitud());
        cliente.setIban(cliente.getIban());
        usuarioRepository.save(cliente);
        return ResponseEntity.ok().body(cliente);
        })
        .orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
      }


    @PostMapping("/propietarios")
    ResponseEntity<Propietario> añadirPropietario(@RequestBody Propietario newCliente) throws URISyntaxException {
      Propietario result = propietarioRepository.save(newCliente);
      return ResponseEntity.created(new URI("/propietarios/" + newCliente.getId())).body(result);
    }

    @PostMapping("/usuarios")
    ResponseEntity<Usuario> añadirUsuario(@RequestBody Usuario newCliente) throws URISyntaxException {
      Usuario result = usuarioRepository.save(newCliente);
      return ResponseEntity.created(new URI("/usuarios/" + newCliente.getId())).body(result);
    }
    
    @GetMapping("/usuariosName/{username}")
    ResponseEntity<Usuario> getUsuarioByName(@PathVariable String username) {
      return usuarioRepository.findByusername(username).map(user -> ResponseEntity.ok().body(user))
                .orElse(new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/propietariosName/{username}")
    ResponseEntity<Propietario> getPropietarioByName(@PathVariable String username) {
      return propietarioRepository.findByusername(username).map(user -> ResponseEntity.ok().body(user))
                .orElse(new ResponseEntity<Propietario>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/propietarios")
      List<Propietario> getPropietarios() {
      return propietarioRepository.findAll();
    }

    @GetMapping("/usuarios")
      List<Usuario> getUsuarios() {
      return usuarioRepository.findAll();
    }
}