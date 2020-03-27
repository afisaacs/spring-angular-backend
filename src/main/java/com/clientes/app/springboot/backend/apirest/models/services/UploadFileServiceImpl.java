package com.clientes.app.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final static String DIR_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreArchivo) throws MalformedURLException {
		Path rutaArchivoAnterior = getPath(nombreArchivo);
		Resource recurso = new UrlResource(rutaArchivoAnterior.toUri());
		log.info(rutaArchivoAnterior.toString());
		if(!recurso.exists() || !recurso.isReadable()) {
			rutaArchivoAnterior = Paths.get("src/main/resources/static/images").resolve("notuser.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivoAnterior.toUri());
		}
		return recurso;
	}

	@Override
	public String guardar(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString().concat("_").concat(archivo.getOriginalFilename().replace(" ", ""));
		Path rutaArchivo = getPath(nombreArchivo);
		Files.copy(archivo.getInputStream(), rutaArchivo);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreArchivo) {
		Path rutaArchivoAnterior = getPath(nombreArchivo);
		File archivoAnterior = rutaArchivoAnterior.toFile();
		if(archivoAnterior.exists() && archivoAnterior.isFile() && archivoAnterior.canRead()) {
			return archivoAnterior.delete();
		}
		return false;
	}

	@Override
	public Path getPath(String nombreImagen) {
		return Paths.get(DIR_UPLOAD).resolve(nombreImagen).toAbsolutePath();
	}

}
