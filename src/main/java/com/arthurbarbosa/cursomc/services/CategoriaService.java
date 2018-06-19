package com.arthurbarbosa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.arthurbarbosa.cursomc.domain.Categoria;
import com.arthurbarbosa.cursomc.repositories.CategoriaRepository;
import com.arthurbarbosa.cursomc.services.exceptions.DataIntegrityException;
import com.arthurbarbosa.cursomc.services.exceptions.ObjectNotFounException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFounException(
				"objeto não encontrado! Id:" + id + ", Tipo: " + Categoria.class.getName()));

	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível remover uma categoria que possui produtos.");
		}
		
	}
	
	
	
	
}
