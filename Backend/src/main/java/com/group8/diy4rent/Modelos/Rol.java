package com.group8.diy4rent.Modelos;

import javax.persistence.*;
import com.group8.diy4rent.Enums.RolEnum;

public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private RolEnum rolNombre;

    public Rol() {
    }

    public Rol(Integer id, RolEnum rolNombre) {
        this.id = id;
        this.rolNombre = rolNombre;
    }

    public Integer getId() {
        return id;
    }

    public RolEnum getRolNombre() {
        return rolNombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(RolEnum rolNombre) {
        this.rolNombre = rolNombre;
    }
}
