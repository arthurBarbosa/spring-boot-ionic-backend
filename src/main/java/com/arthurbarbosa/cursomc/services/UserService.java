package com.arthurbarbosa.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.arthurbarbosa.cursomc.security.UserSS;

public class UserService {
	//metodo para retornar o usuario logado
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}
}
