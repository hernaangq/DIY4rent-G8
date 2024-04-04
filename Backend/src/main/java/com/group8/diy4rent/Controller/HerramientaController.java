package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Repository.HerramientaRepository;

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

public class HerramientaController {
    private final HerramientaRepository herramientaRepository;

    public HerramientaController(HerramientaRepository herramientaRepository) {
        this.herramientaRepository = herramientaRepository;
    }

    @GetMapping("/propietarios/herramientas")
	List<Herramienta> getHerramientas() {
	// return (List<herramienta>) herramientaRepository.findBynombre("hernan");
	return herramientaRepository.findAll();

	}

    @GetMapping("/propietarios/herramientas/{id}")
	ResponseEntity<Herramienta> getHerramienta(@PathVariable Integer id) {
	// return (List<herramienta>) herramientaRepository.findBynombre("hernan");
	return  herramientaRepository.findById(id).map(h -> ResponseEntity.ok().body(h))
            .orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));

	}

    @PostMapping(path="/propietarios/herramientas") // Map ONLY POST Requests
	public @ResponseBody String addNewClient (@RequestBody Herramienta herramientarequest) {
	  // @ResponseBody means the returned String is the response, not a view name
	  // @RequestParam means it is a parameter from the GET or POST request


	  Herramienta n = new Herramienta();
	  n.setNombre(herramientarequest.getNombre());
	  n.setEstado(herramientarequest.getEstado());
	  n.setFoto(herramientarequest.getFoto());
	  n.setPrecio(herramientarequest.getPrecio());
	  n.setEstaAlquilada(herramientarequest.getEstaAlquilada());
	  n.setFechasDisponibles(herramientarequest.getFechasDisponibles());
	  herramientaRepository.save(n);
	  return "Saved";
	}
  
}
