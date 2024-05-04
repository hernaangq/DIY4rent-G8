package com.group8.diy4rent.Controller;

import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Repository.PropietarioRepository;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.beans.*;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.http.HttpHeaders;




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
	@PostMapping("/herramientas/{propietario_userName}")
	ResponseEntity<Herramienta> anadirHerramienta(@RequestBody Herramienta newHerramienta, @PathVariable String propietario_userName) throws URISyntaxException {
		Propietario propietario = propietarioRepository.findByusername(propietario_userName).orElseThrow(() -> new ResponseStatusException(
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
	@GetMapping("/herramientas/propietario/{propietario_userName}")
	List<Herramienta> readHerramientasDe(@PathVariable String propietario_userName) throws URISyntaxException {
		Propietario propietario = propietarioRepository.findByusername(propietario_userName).orElseThrow(() -> new ResponseStatusException(
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
			if(newHerramienta.getEstaAlquilada() != null){
				herramienta.setEstaAlquilada(newHerramienta.getEstaAlquilada());
			}
			if(newHerramienta.getPrecio() != null){
				herramienta.setPrecio(newHerramienta.getPrecio());
			}

			herramientaRepository.save(herramienta);
			return ResponseEntity.ok().body(herramienta);
		}).orElse(new ResponseEntity<Herramienta>(HttpStatus.NOT_FOUND));
	}



	@PutMapping(value = "/herramientas/{id}/foto", consumes = "application/jpg")
	@io.swagger.v3.oas.annotations.Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/jpg", schema = @Schema(type = "string", format = "binary")) }))
	public ResponseEntity<?> subeFoto(@PathVariable Integer id,
			@RequestBody byte[] fileContent) {
		return herramientaRepository.findById(id).map(herramienta -> {
			herramienta.setFoto(fileContent);
			herramientaRepository.save(herramienta);
			return ResponseEntity.ok("Foto subida correctamente");
		}).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Herramienta no encontrada"));
	}

	@PostMapping(value = "/herramientas/{id}/foto", consumes = "multipart/form-data")
public ResponseEntity<?> subeFotoNuevo(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
    return herramientaRepository.findById(id).map(herramienta -> {
        try {
            herramienta.setFoto(file.getBytes());
            herramientaRepository.save(herramienta);
            return ResponseEntity.ok("Foto subida correctamente");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al leer el archivo", e);
        }
    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Herramienta no encontrada"));
}



	@GetMapping(value = "/herramientas/{id}/foto", produces = "application/jpg")
	public ResponseEntity<?> descargaFoto(@PathVariable Integer id) {
		Herramienta herramienta = herramientaRepository.findById(id).orElseThrow(
			() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
				"Herramienta no encontrada"));
		if (herramienta.getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok()
			.header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"herramienta_foto_" + id + ".jpg" + "\"")
			.body(new ByteArrayResource(herramienta.getFoto()));
	}




}
