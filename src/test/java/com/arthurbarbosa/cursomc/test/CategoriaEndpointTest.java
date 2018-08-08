package com.arthurbarbosa.cursomc.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.el.parser.AstListData;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.arthurbarbosa.cursomc.domain.Categoria;
import com.arthurbarbosa.cursomc.repositories.CategoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // para utilizar uma porta aleatoria
@AutoConfigureMockMvc
public class CategoriaEndpointTest {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	
	@MockBean
	private CategoriaRepository categoriaRepository;
	
	private MockMvc mockMvc;
	
	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthorization("arthurbruno03@gmail.com", "123");
		}
	}
	
	@Ignore
	@Test
	public void listCategoriaWhenUserNameAndPasswordAreIncorretShouldReturnStatusCode401() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/categorias", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void listCategoriaWhenUserNameAndPasswordAreIncorretShouldReturnStatusCode200() {
		List<Categoria> categorias = Arrays.asList(new Categoria(null, "arthur")); 
		BDDMockito.when(categoriaRepository.findAll()).thenReturn(categorias);
		ResponseEntity<String> response = restTemplate.getForEntity("/categorias", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
}
