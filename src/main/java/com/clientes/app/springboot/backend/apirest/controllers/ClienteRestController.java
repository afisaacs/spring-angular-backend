package com.clientes.app.springboot.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clientes.app.springboot.backend.apirest.models.entity.Cliente;
import com.clientes.app.springboot.backend.apirest.models.services.IClienteService;
import com.clientes.app.springboot.backend.apirest.models.services.IUploadFileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadFileService;

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

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
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

	@Secured("ROLE_ADMIN")
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

	@Secured("ROLE_ADMIN")
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
				clienteActual.setCreateAt(cliente.getCreateAt());
				clienteActual.setRegion(cliente.getRegion());
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

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			Cliente cliente = clienteService.findById(id);
			if (cliente == null) {
				ok = false;
				mensaje = "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos");
				status = HttpStatus.NOT_FOUND;
			} else {
				if (cliente.getImagen() != null && !cliente.getImagen().isEmpty()) {
					Path rutaArchivoAnterior = Paths.get("uploads").resolve(cliente.getImagen()).toAbsolutePath();
					File archivoAnterior = rutaArchivoAnterior.toFile();
					if(archivoAnterior.exists() && archivoAnterior.isFile() && archivoAnterior.canRead()) {
						archivoAnterior.delete();
					}
				}
				clienteService.delete(id);
				ok = true;
				mensaje = "Cliente eliminado satisfactoriamente";
				status = HttpStatus.OK;
			}
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

	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("cliente/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile archivo, @RequestParam Long id) {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			Cliente cliente = clienteService.findById(id);
			if (cliente == null) {
				ok = false;
				mensaje = "El cliente ID: ".concat(id.toString()).concat(" no existe en la base de datos");
				status = HttpStatus.NOT_FOUND;
			} else if (archivo.isEmpty()) {
				ok = false;
				mensaje = "Cargar un archivo es obligatorio";
				status = HttpStatus.BAD_REQUEST;
			} else {
				if (cliente.getImagen() != null && !cliente.getImagen().isEmpty()) {
					if(uploadFileService.eliminar(cliente.getImagen())) {
						log.info("Imagen ".concat(cliente.getImagen()).concat(" eliminada"));
					} else {
						log.info("No fue posible eliminar la imagen ".concat(cliente.getImagen()));
					}
				}
				String nombreArchivo = uploadFileService.guardar(archivo);
				cliente.setImagen(nombreArchivo);
				clienteService.save(cliente);
				ok = true;
				mensaje = "Imagen cargada correctamente al cliente ".concat(cliente.getNombre()).concat(" ").concat(cliente.getApellido());
				status = HttpStatus.CREATED;
				response.put("cliente", cliente);
			}
		} catch (DataAccessException e) {
			ok = false;
			mensaje= "Error eliminando cliente en la base de datos";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		} catch (IOException e) {
			ok = false;
			mensaje= "Error subiendo el archivo al servidor";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("error", e.getMessage().concat(": ").concat(e.toString()));
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/upload/img/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen) {
		Resource recurso = null;
		try {
			recurso = uploadFileService.cargar(nombreImagen);
		} catch (MalformedURLException e) {
			log.error("Error", e);
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(nombreImagen).concat("\""));
		return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
	}

}
