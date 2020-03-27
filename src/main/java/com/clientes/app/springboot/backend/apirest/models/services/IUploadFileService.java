package com.clientes.app.springboot.backend.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource cargar(String nombreImagen) throws MalformedURLException;
	
	public String guardar(MultipartFile archivo) throws IOException;
	
	public boolean eliminar(String nombreImagen);
	
	public Path getPath(String nombreFoto);

}
