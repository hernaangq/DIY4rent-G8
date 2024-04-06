package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Repository.HerramientaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
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

public class HerramientaController {
    private final HerramientaRepository herramientaRepository;
	private final PropietarioRepository propietarioRepository;

    public HerramientaController(HerramientaRepository herramientaRepository, PropietarioRepository propietarioRepository) {
        this.herramientaRepository = herramientaRepository;
		this.propietarioRepository = propietarioRepository;
    }

	//OKEY
    @GetMapping("/herramientas")
	List<Herramienta> getHerramientas() {
	return herramientaRepository.findAll();
	}

	//OKEY
	@PostMapping("/herramientas/{propietario_id}")
    ResponseEntity<Herramienta> anadirHerramienta(@RequestBody Herramienta newHerramienta, @PathVariable Integer propietario_id) throws URISyntaxException {
		Propietario propietario = propietarioRepository.findById(propietario_id).orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Propietario no encontrado"));
		newHerramienta.setPropietario(propietario);
		Herramienta result = herramientaRepository.save(newHerramienta);
      return ResponseEntity.created(new URI("/herramientas/" + newHerramienta.getId())).body(result);
    }

	//OKEY
	@GetMapping("/herramientas/{id}")
    ResponseEntity<Herramienta> readOne(@PathVariable Integer id) throws URISyntaxException {
		return herramientaRepository.findById(id).map(herramienta ->
		ResponseEntity.ok().body(herramienta)
		).orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));
    }

	//@GetMapping("/herramientas/propietario/{propietario_id}")
    //ResponseEntity<Herramienta> readHerramientasDe(@PathVariable Integer propietario_id) throws URISyntaxException {
	//	Propietario propietario = propietarioRepository.findById(propietario_id).orElseThrow(() -> new ResponseStatusException(
	//			HttpStatus.NOT_FOUND, "Propietario no encontrado"));
		
		
	//	return ((Optional<Propietario>) herramientaRepository.findByPropietario(propietario)).map(herramientas ->
	//	ResponseEntity.ok().body(herramienta)
	//	).orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));
    //S}












	/* 
	@GetMapping("/propietarios/herramientas/{id}")
	ResponseEntity<Herramienta> getHerramienta(@PathVariable Integer id) {
	// return (List<herramienta>) herramientaRepository.findBynombre("hernan");
	return herramientaRepository.findById(id).map(h -> ResponseEntity.ok().body(h))
            .orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));

	}
	@PutMapping("/propietarios/herramientas/{id}")
	public ResponseEntity<Herramienta> actualizarHerramienta (@RequestBody Herramienta herramienta, @PathVariable Integer id) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
  
        return herramientaRepository.findById(id).map(c -> {
        
        herramienta.setNombre(herramienta.getNombre());
        herramienta.setEstado(herramienta.getEstado());
	 	herramienta.setFoto(herramienta.getFoto());
	 	herramienta.setPrecio(herramienta.getPrecio());
	 	herramienta.setEstaAlquilada(herramienta.getEstaAlquilada());
	 	herramienta.setFechasDisponibles(herramienta.getFechasDisponibles());
	 	herramientaRepository.save(herramienta);
        return ResponseEntity.ok().body(herramienta);
        })
        .orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));
      }
	*/


}
