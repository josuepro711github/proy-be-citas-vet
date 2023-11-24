package com.utp.edu.pe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImagenService {
    String cargarImagen(MultipartFile imagen, String carpeta);

    void eliminarImagen(String nombreImg,String carpeta);
}

