package com.group8.diy4rent.Modelos;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios")
public class Usuario extends Cliente {
    // Como hereda de cliente y no tiene ningún atributo extra, no hace falta emter getters setters o atributos aquí
}
