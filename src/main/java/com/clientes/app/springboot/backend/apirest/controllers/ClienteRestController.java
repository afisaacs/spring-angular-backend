package com.clientes.app.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.app.springboot.backend.apirest.models.entity.Cliente;
import com.clientes.app.springboot.backend.apirest.models.services.IClienteService;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/cliente")
	public ResponseEntity<?> index() {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			List<Cliente> clientes = clienteService.findAll();
			ok = true;
			status = HttpStatus.OK;
			mensaje = "Clientes encontrados satisfactoriamente";
			response.put("clientes", clientes);
		} catch (DataAccessException e) {
			ok = false;
			mensaje = "Error al realizar consulta en la base de datos";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	
	@GetMapping("/cliente/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			Page<Cliente> pageClientes = clienteService.findAll(PageRequest.of(page, 5));
			ok = true;
			status = HttpStatus.OK;
			mensaje = "Clientes encontrados satisfactoriamente";
			response.put("page", pageClientes);
		} catch (DataAccessException e) {
			ok = false;
			mensaje = "Error al realizar consulta en la base de datos";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("cliente/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			Cliente cliente = clienteService.findById(id);
			ok = cliente != null;
			if (ok) {
				response.put("cliente", cliente);
				mensaje = "Cliente encontrado satisfactoriamente";
				status = HttpStatus.OK;
			} else {
				mensaje = "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos");
				status = HttpStatus.NOT_FOUND;
			}
		} catch (DataAccessException e) {
			ok = false;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			mensaje= "Error al realizar consulta en la base de datos";
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@PostMapping("/cliente")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		if(result.hasErrors()) {
			ok = false;
			status = HttpStatus.BAD_REQUEST;
			mensaje = "Errores en la solicitud";
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(fieldError -> fieldError.getField().concat(": ").concat(fieldError.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errors);
		} else {
			try {
				Cliente clienteNuevo = clienteService.save(cliente);
				ok = true;
				mensaje = "Cliente creado satisfactoriamete";
				status = HttpStatus.CREATED;
				response.put("cliente", clienteNuevo);
			} catch (DataAccessException e) {
				ok = false;
				mensaje= "Error al crear cliente en la base de datos";
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@PutMapping("/cliente/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id, BindingResult result) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		if(result.hasErrors()) {
			ok = false;
			mensaje = "Errores en la solicitud";
			List<String> errores = result.getFieldErrors()
					.stream()
					.map(fieldError -> fieldError.getField().concat(": ").concat(fieldError.getDefaultMessage()))
					.collect(Collectors.toList());
			response.put("errores", errores);
		} else {
			try {
				Cliente clienteActual = clienteService.findById(id);
				clienteActual.setApellido(cliente.getApellido());
				clienteActual.setNombre(cliente.getNombre());
				clienteActual.setEmail(cliente.getEmail());
				cliente = clienteService.save(clienteActual);
				ok = true;
				mensaje = "Cliente editado satisfactoriamente";
				status = HttpStatus.CREATED;
				response.put("cliente", cliente);
			} catch (DataAccessException e) {
				ok = false;
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				mensaje = "Error actualizando cliente en la base de datos";
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			}
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			clienteService.delete(id);
			ok = true;
			mensaje = "Cliente eliminado satisfactoriamente";
			status = HttpStatus.OK;
		} catch (DataAccessException e) {
			ok = false;
			mensaje= "Error eliminando cliente en la base de datos";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

}
