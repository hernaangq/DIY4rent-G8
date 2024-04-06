package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Repository.PropietarioRepository;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Repository.HerramientaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

	//OKEY
	@GetMapping("/herramientas/propietario/{propietario_id}")
	List<Herramienta> readHerramientasDe(@PathVariable Integer propietario_id) throws URISyntaxException {
		Propietario propietario = propietarioRepository.findById(propietario_id).orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND, "Propietario no encontrado"));
		
		
		return herramientaRepository.findByPropietario(propietario);
    }

	//OKEY
	@DeleteMapping("/herramientas/{id}")
	ResponseEntity<Herramienta> delete(@PathVariable Integer id) {
		herramientaRepository.deleteById(id);
		return ResponseEntity.ok().body(null);
	}


	@PatchMapping("/herramientas/{id}")
	ResponseEntity<Herramienta> partialUpdate(@RequestBody Herramienta newHerramienta, @PathVariable Integer id) {
		return herramientaRepository.findById(id).map(herramienta -> {
			if(newHerramienta.getEstado() != null) {
				herramienta.setEstado(newHerramienta.getEstado());
			}
			if(newHerramienta.getFechaFinal() != null) {
				herramienta.setFechaFinal(newHerramienta.getFechaFinal());
			}
			if(newHerramienta.getFechaInicio() != null){
				herramienta.setFechaInicio(newHerramienta.getFechaInicio());
			}
			if(newHerramienta.getFoto() != null){
				herramienta.setFoto(newHerramienta.getFoto());
			}
			if(newHerramienta.getNombre() != null){
				herramienta.setNombre(newHerramienta.getNombre());
			}

			herramientaRepository.save(herramienta);
			return ResponseEntity.ok().body(herramienta);
		}).orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));
	}








}
