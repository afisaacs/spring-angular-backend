package com.clientes.app.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 5653189531742613427L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String apellido;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true, length = 20, nullable = false)
	private String username;
	
	@Column(length = 60, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles",
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})},
	joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name="rol_id"))
	private List<Rol> roles;

}
