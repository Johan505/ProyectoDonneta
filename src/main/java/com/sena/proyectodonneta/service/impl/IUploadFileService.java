package com.sena.proyectodonneta.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

    // Método para copiar archivo con nombre único
    public String copy(MultipartFile file) throws IOException;

    // Método para mostrar un recurso de imagen
    public Resource load(String filename) throws MalformedURLException;

    // Método para borrar un archivo de imagen existente
    public boolean delete(String filename);
}
