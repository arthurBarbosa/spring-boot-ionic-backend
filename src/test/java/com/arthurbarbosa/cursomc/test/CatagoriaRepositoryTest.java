package com.arthurbarbosa.cursomc.test;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.amazonaws.services.organizations.model.ConstraintViolationException;
import com.arthurbarbosa.cursomc.domain.Categoria;
import com.arthurbarbosa.cursomc.repositories.CategoriaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED.NONE)
public class CatagoriaRepositoryTest {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void createShouldPersistData() {
		Categoria cat = new Categoria(null, "info");
		this.categoriaRepository.save(cat);
		Assertions.assertThat(cat.getId()).isNotNull();
		Assertions.assertThat(cat.getNome()).isEqualTo("info");
	}
	
	@Test
	public void createWhenNameIsNullShouldConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("o campo nome é obrigatorio");
		this.categoriaRepository.save(new Categoria());
		
	}
}
