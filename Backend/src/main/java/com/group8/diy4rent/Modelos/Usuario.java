package com.group8.diy4rent.Modelos;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USUARIO")
public class Usuario extends Cliente {
    // Como hereda de cliente y no tiene ningún atributo extra, no hace falta emter getters setters o atributos aquí
    
    // Un alquiler está asociado a un usuario único (one) y varios alquileres pueden estar asociados a un usuario (many)
    @OneToMany(mappedBy = "usuario")
    private List<Alquiler> alquileres;


}
