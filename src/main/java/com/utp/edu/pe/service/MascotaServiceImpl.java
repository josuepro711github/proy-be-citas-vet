package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Mascota;

import com.utp.edu.pe.repository.MascotaRepository;

import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MascotaServiceImpl implements MascotaService{

    @Autowired
    private PropertiesInterno propertiesInterno;
    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private ImagenService imagenService;

    @Override
    public BodyResponse registrarMascota(Mascota request, MultipartFile imagen) {
        BodyResponse response = new BodyResponse();


        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());

        // Convertir Timestamp a Date
        Date fecha = new Date(fechaActual.getTime());

        // Formatear la fecha como una cadena
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = dateFormat.format(fecha);

        String nombreImagen = imagenService.cargarImagen(imagen,fechaFormateada);
        request.setImagen(nombreImagen);

        mascotaRepository.save(request);


        response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
        response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        return response;
    }


}
