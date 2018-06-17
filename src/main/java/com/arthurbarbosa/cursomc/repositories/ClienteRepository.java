package com.arthurbarbosa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arthurbarbosa.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
