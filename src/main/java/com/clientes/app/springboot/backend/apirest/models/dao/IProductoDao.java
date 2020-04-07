package com.clientes.app.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.clientes.app.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {
	
	@Query("SELECT p FROM Producto p WHERE p.nombre like %?1%")
	public List<Producto> findByNombre(String nombre);
	
	public List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String nombre);

}
