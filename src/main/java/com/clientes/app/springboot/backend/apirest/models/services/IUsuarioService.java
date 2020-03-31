package com.clientes.app.springboot.backend.apirest.models.services;

import com.clientes.app.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
