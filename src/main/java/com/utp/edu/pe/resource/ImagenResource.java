package com.utp.edu.pe.resource;

import com.utp.edu.pe.util.Constantes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.FileTypeMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(Constantes.BASEPATH+"/imagen")
public class ImagenResource {

    @GetMapping("/{carpeta}/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String carpeta,@PathVariable String fileName) throws IOException {
        System.out.println(fileName);
        // Obtener la imagen del archivo
        Path imagePath = Paths.get("src/main/resources/imagenes/" + carpeta+"/"+fileName);
        File imageFile = imagePath.toFile();
        System.out.println(imageFile);

        // Verificar si el archivo existe y es accesible
        if (Files.exists(imagePath) && Files.isReadable(imagePath)) {
            byte[] imageBytes = Files.readAllBytes(imagePath);

            // Obtener el tipo de contenido según el formato de la imagen
            String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(imageFile);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
