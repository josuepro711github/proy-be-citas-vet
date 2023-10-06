package com.utp.edu.pe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {
    String cargarImagen(MultipartFile imagen, String fecha);
    void eliminarImagen(String nombreImg);
}

