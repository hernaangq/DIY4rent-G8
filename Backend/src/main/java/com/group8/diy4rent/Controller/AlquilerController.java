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
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
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

    @PostMapping("/alquileres/{usuario_id}/{herramienta_id}")
    ResponseEntity<Alquiler> anadirAlquiler(@RequestBody Alquiler newAlquiler, @PathVariable Integer usuario_id, @PathVariable Integer herramienta_id) throws URISyntaxException {
        Usuario usuario = usuarioRepository.findById(usuario_id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        newAlquiler.setUsuario(usuario);

        Herramienta herramienta = herramientaRepository.findById(herramienta_id).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Herramienta no encontrada"));
        newAlquiler.setHerramienta(herramienta);

        Alquiler result = alquilerRepository.save(newAlquiler);
        return ResponseEntity.created(new URI("/alquileres/" + newAlquiler.getId())).body(result);
    }

    @PatchMapping("/alquileres/{id}")
    ResponseEntity<Alquiler> partialUpdate(@RequestBody Alquiler newAlquiler, @PathVariable Integer id) {
        return alquilerRepository.findById(id).map(alquiler -> {
            if(newAlquiler.getEstrellaUsuario() != null){
                alquiler.setEstrellaUsuario(newAlquiler.getEstrellaUsuario());
            }
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