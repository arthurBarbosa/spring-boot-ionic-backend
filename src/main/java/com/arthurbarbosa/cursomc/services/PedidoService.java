package com.arthurbarbosa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arthurbarbosa.cursomc.domain.Pedido;
import com.arthurbarbosa.cursomc.repositories.PedidoRepository;
import com.arthurbarbosa.cursomc.services.exceptions.ObjectNotFounException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFounException(
				"objeto não encontrado! Id:" + id + ", Tipo: " + Pedido.class.getName()));

	}
}
