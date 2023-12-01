package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Mascota;

import com.utp.edu.pe.model.Raza;
import com.utp.edu.pe.repository.MascotaRepository;

import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.hibernate.HibernateException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.PrintWriter;
import java.io.StringWriter;
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


//        Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
//
//        // Convertir Timestamp a Date
//        Date fecha = new Date(fechaActual.getTime());
//
//        // Formatear la fecha como una cadena
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String fechaFormateada = dateFormat.format(fecha);
//
//        String nombreImagen = imagenService.cargarImagen(imagen,"mascotas");
//        request.setImagen(nombreImagen);
//
//
//        int j = 0;
//        String[] nombresH = {"Josue","Maximo","Brad","Moises","Juan","Jesus","Miguel","Angelo"};
//        String[] nombresF = {"Ingrid","Genesis","Valeria","Diana","Angie","Maria","Katy","Leydi"};
//        for(int i = 0; i<40; i++){
//            request.setId_mascota(0);
//            System.out.println(nombresF[j]);
//            if(j == 7){
//                j=0;
//            }
//            if(i<=23){
//                request.setAlias(nombresH[j]);
//                request.setGenero("Masculino");
//
//            }else {
//                request.setAlias(nombresF[j]);
//                request.setGenero("Femenino");
//                Raza r = new Raza();
//                r.setId_raza(2);
//                request.setRaza(r);
//            }
//
//            mascotaRepository.save(request);
//            j++;
//        }

        try {
            String nombreImagen = imagenService.cargarImagen(imagen,"mascotas");
            request.setImagen(nombreImagen);
            mascotaRepository.save(request);

            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);
        } catch (DataIntegrityViolationException e) {

            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));

        }

        return response;
    }

    @Override
    public BodyResponse actualizarMascota(Mascota request, MultipartFile imagen) {
        BodyResponse response = new BodyResponse();


        try {
            Mascota mascotaEncontrada = mascotaRepository.findById(request.getId_mascota()).orElse(null);
            if(mascotaEncontrada == null){
                return null;
            }
            if(request.getImagen().equals("cambiado")){
                imagenService.eliminarImagen(mascotaEncontrada.getImagen(),"mascotas");
                String nombreImagen = imagenService.cargarImagen(imagen,"mascotas");
                request.setImagen(nombreImagen);
            }
            mascotaRepository.save(request);

            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);
        } catch (DataIntegrityViolationException e) {

            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));

        }

        return response;
    }

    @Override
    public Page<Mascota> listarMascota(Pageable pageable) {
        Page<Mascota> listaMascota = mascotaRepository.findAll(pageable);
        System.out.println("Lista Mascota: " + mascotaRepository.findAll());
        return listaMascota;
    }


}
