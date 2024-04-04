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
public class PropietarioController {

    private final PropietarioRepository propietarioRepository;

    public PropietarioController(PropietarioRepository propietarioRepository) {
        this.propietarioRepository = propietarioRepository;
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


}
