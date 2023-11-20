package com.utp.edu.pe.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ImagenServiceImpl implements ImagenService {


    @Override
    public String cargarImagen(MultipartFile imagen, String fecha) {
        String nombreImg = " ";

        try {
            fecha = fecha.replaceAll("[^a-zA-Z0-9_-]", "_");
            nombreImg = fecha+"_"+imagen.getOriginalFilename();
            Path targetPath = Path.of("src/main/resources/imagenes/", nombreImg);

            Files.copy(imagen.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return nombreImg;

        } catch (IOException ex) {
            throw new RuntimeException("No se pudo almacenar el archivo " + imagen.getOriginalFilename() + ". ¡Inténtalo de nuevo!",ex);
        }

    }

    @Override
    public String cargarImagenCliente(MultipartFile imagen) {

        String nombreImg = " ";

        try {
            Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

            // Convertir Timestamp a Date
            Date fecha = new Date(fechaActual.getTime());

            // Formatear la fecha como una cadena
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = dateFormat.format(fecha).replaceAll("[^a-zA-Z0-9_-]", "_");

            nombreImg = fechaFormateada+"_"+imagen.getOriginalFilename();
            Path targetPath = Path.of("src/main/resources/imagenes/clientes/", nombreImg);

            Files.copy(imagen.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
            return nombreImg;

        } catch (IOException ex) {
            throw new RuntimeException("No se pudo almacenar el archivo " + imagen.getOriginalFilename() + ". ¡Inténtalo de nuevo!",ex);
        }
    }

    @Override
    public void eliminarImagen(String nombreImg) {
        Path filePath = Path.of("src/main/resources/imagenes/", nombreImg);

        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

