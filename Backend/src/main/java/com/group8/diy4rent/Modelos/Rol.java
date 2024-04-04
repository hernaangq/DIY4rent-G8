package com.group8.diy4rent.Modelos;
import com.group8.diy4rent.Enums.RolEnum;

import javax.persistence.*;
// import javax.validation.constraints.NotNull;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @NotNull
    @Enumerated(EnumType.STRING)
    private RolEnum rol;

    public Rol() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolEnum getRol() {
        return rol;
    }

    public void setRol(RolEnum rol) {
        this.rol = rol;
    }
}

