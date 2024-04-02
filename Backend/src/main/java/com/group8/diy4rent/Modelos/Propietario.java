package com.group8.diy4rent.Modelos;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "Propietarios")
public class Propietario extends Cliente {
    // Como hereda de cliente y no tiene ningún atributo extra, no hace falta emter getters setters o atributos aquí

    // Un propietario es dueño de una o varias herramientas
    @OneToMany(mappedBy = "propietario")
    List<Herramienta> herramienta;
}
