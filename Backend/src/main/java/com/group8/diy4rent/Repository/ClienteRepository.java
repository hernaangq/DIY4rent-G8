package com.group8.diy4rent.Repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.group8.diy4rent.Modelos.Cliente;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}

/*
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
*/