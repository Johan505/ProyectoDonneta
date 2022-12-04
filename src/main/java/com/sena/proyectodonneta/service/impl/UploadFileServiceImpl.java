package com.sena.proyectodonneta.service.impl;

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

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	// 1. Definir un atributo estático para definir el directorio donde guardaremos
	// los archivos
	private final static String UPLOADS_FOLDER = "uploads";

	@Override
	public Resource load(String filename) throws MalformedURLException {
		Path pathFoto = getPath(filename);

		Resource recurso = new UrlResource(pathFoto.toUri());

		if (!recurso.exists() || !recurso.isReadable()) {
			throw new RuntimeException("Error: no se puede cargar la imagen: " + pathFoto.toString());
		}
		return recurso;
	}

	// 3. Implementar método copy
	@Override
	public String copy(MultipartFile file) throws IOException {
		// Recibe el nombre original del archivo
		// Mediante la clase randomUUID generaremos un aleatorio, que garantice que el
		// nombre del archivo
		// Jamás será igual, esto garantiza que los archivos no se sobree
		String uniqueFilename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		Path rootPath = getPath(uniqueFilename);
		// Movemos el archivo al directorio establecido en el rootPath "uploads"
		Files.copy(file.getInputStream(), rootPath);
		// Devolvemos el nuevo nombre del archivo
		return uniqueFilename;
	}

	// 2. Definimos el método para obtener la ruta donde guardaremos el archivo
	public Path getPath(String filename) {
		return Paths.get(UPLOADS_FOLDER).resolve(filename).toAbsolutePath();
	}

	@Override
	public boolean delete(String filename) {
		Path rootPath = getPath(filename);
		File archivo = rootPath.toFile();
		// Si archivo existe y se puede leer, borrarlo y retornar true de lo contrario
		// false
		if (archivo.exists() && archivo.canRead()) {
			if (archivo.delete()) {
				return true;
			}
		}
		return false;

	}

}
