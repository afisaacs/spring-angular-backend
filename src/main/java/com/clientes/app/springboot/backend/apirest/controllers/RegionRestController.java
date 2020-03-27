package com.clientes.app.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.app.springboot.backend.apirest.models.entity.Region;
import com.clientes.app.springboot.backend.apirest.models.services.IRegionService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class RegionRestController {
	
	@Autowired
	private IRegionService regionService;
	
	@GetMapping("/region")
	private ResponseEntity<?> index() {
		Map<String, Object> response = new HashMap<>();
		Boolean ok = null;
		String mensaje = null;
		HttpStatus status = null;
		try {
			List<Region> regiones = regionService.findAll();
			ok = true;
			status = HttpStatus.OK;
			mensaje = regiones.isEmpty() ? "No hay registros en la base de datos" : "Regiones encontradas satisfactoriamente";
			response.put("regiones", regiones);
		} catch (DataAccessException e) {
			ok = false;
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
		}
		response.put("mensaje", mensaje);
		response.put("ok", ok);
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	

}
