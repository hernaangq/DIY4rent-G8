package com.group8.diy4rent.Controller;

// import com.group8.diy4rent.Modelos.Cliente;
// import com.group8.diy4rent.Repository.ClienteRepository;
import com.group8.diy4rent.Modelos.Herramienta;
import com.group8.diy4rent.Modelos.Propietario;
import com.group8.diy4rent.Modelos.Usuario;
import com.group8.diy4rent.Repository.HerramientaRepository;
import com.group8.diy4rent.Repository.UsuarioRepository;
import com.group8.diy4rent.Modelos.Alquiler;
import com.group8.diy4rent.Repository.AlquilerRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;
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
import javax.persistence.*;
import java.util.Date;




@RestController
@CrossOrigin
public class AlquilerController {

    private final AlquilerRepository alquilerRepository;
    private final UsuarioRepository usuarioRepository;
    private final HerramientaRepository herramientaRepository;

    public AlquilerController(AlquilerRepository alquilerRepository, UsuarioRepository usuarioRepository, HerramientaRepository herramientaRepository) {
        this.alquilerRepository = alquilerRepository;
        this.usuarioRepository = usuarioRepository;
        this.herramientaRepository = herramientaRepository;
    }

    @GetMapping("/alquileres")
    List<Alquiler> getAllAlquileres() {
        return alquilerRepository.findAll();
        
    }

    @GetMapping("/alquileres/herramienta/{herramienta_id}")
	List<Alquiler> readHerramientasDe(@PathVariable Integer herramienta_id) throws URISyntaxException {
        Herramienta herramienta = herramientaRepository.findById(herramienta_id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Herramienta no encontrada"));
		
        return alquilerRepository.findByHerramienta(herramienta);
    }

    @GetMapping("/alquileres/usuario/{userName}")
    List<Alquiler> readAlquileresDe(@PathVariable String userName) throws URISyntaxException {
        Usuario usuario = usuarioRepository.findByusername(userName).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        
        return alquilerRepository.findByUsuario(usuario);
    }




    @PostMapping("/alquileres/{userName}/{herramienta_id}")
    ResponseEntity<Alquiler> anadirAlquiler(@PathVariable String userName, @PathVariable Integer herramienta_id, @RequestParam("fecha1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha1, @RequestParam("fecha2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha2) throws URISyntaxException {
        Alquiler newAlquiler = new Alquiler();

        Usuario usuario = usuarioRepository.findByusername(userName).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        
        newAlquiler.setUsuario(usuario);

        Herramienta herramienta = herramientaRepository.findById(herramienta_id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Herramienta no encontrada"));
        newAlquiler.setHerramienta(herramienta);

        //long diffInMillies = Math.abs(herramienta.getFechaFinal().getTime() - herramienta.getFechaInicio().getTime());
        //long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);


        newAlquiler.setFechaInicioAlquiler(fecha1); 
        newAlquiler.setFechaFinalAlquiler(fecha2);


        Alquiler result = alquilerRepository.save(newAlquiler);
        return ResponseEntity.created(new URI("/alquileres/" + newAlquiler.getId())).body(result);
    }

    @PatchMapping("/alquileres/{id}")
    ResponseEntity<Alquiler> partialUpdate(@RequestBody Alquiler newAlquiler, @PathVariable Integer id) {
        return alquilerRepository.findById(id).map(alquiler -> {
            if(newAlquiler.getEstrellasServicio() != null){
                alquiler.setEstrellasServicio(newAlquiler.getEstrellasServicio());
            }
            if(newAlquiler.getValoracion() != null){
                alquiler.setValoracion(newAlquiler.getValoracion());
            }

            alquilerRepository.save(alquiler);
            return ResponseEntity.ok().body(alquiler);
        }).orElse(new ResponseEntity<Alquiler>(HttpStatus.NOT_FOUND));
    }


}