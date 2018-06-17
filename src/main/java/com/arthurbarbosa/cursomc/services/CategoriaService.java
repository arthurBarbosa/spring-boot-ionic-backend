package com.arthurbarbosa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurbarbosa.cursomc.domain.Categoria;
import com.arthurbarbosa.cursomc.repositories.CategoriaRepository;
import com.arthurbarbosa.cursomc.services.exceptions.ObjectNotFounException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj =  repo.findById(id);
		if(obj == null) {
			throw new ObjectNotFounException("objeto não encontrado! Id:" + id +
					", Tipo: " + Categoria.class.getName());
		}
		
		return obj.orElse(null);
	}
}
