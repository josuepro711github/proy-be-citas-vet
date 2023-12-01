package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Cita;
import com.utp.edu.pe.model.CitaMascota;
import com.utp.edu.pe.model.Usuario;
import com.utp.edu.pe.repository.CitaMascotaRepository;
import com.utp.edu.pe.repository.CitaRepository;
import com.utp.edu.pe.repository.UsuarioRepository;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CitaServiceImpl implements CitaService{

    @Autowired
    private PropertiesInterno propertiesInterno;
    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private CitaMascotaRepository citaMascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public CitaMascota registrarCita(CitaMascota request) {

        CitaMascota response = new CitaMascota();

        try {


            Cita citaRegistrada = citaRepository.save(request.getCita());

            CitaMascota citaMasNew = new CitaMascota();
            citaMasNew.setCita(citaRegistrada);
            citaMasNew.setMascota(request.getMascota());

            response = citaMascotaRepository.save(citaMasNew);



        } catch (DataIntegrityViolationException e){
            System.out.println("error: "+ e.getMessage());
        }

        return response;
    }

    @Override
    public CitaMascota actualizarCita(CitaMascota request) {
        CitaMascota response = new CitaMascota();

        try {
            Cita citaEncontrada = citaRepository.findById(request.getCita().getId_cita()).orElse(null);
            if(citaEncontrada!=null){
                return null;
            }

            Cita citaRegistrada = citaRepository.saveAndFlush(request.getCita());

            CitaMascota citaMasNew = new CitaMascota();
            citaMasNew.setCita(citaRegistrada);
            citaMasNew.setMascota(request.getMascota());

            response = citaMascotaRepository.save(citaMasNew);



        } catch (DataIntegrityViolationException e){
            System.out.println("error: "+ e.getMessage());
        }

        return response;
    }

    @Override
    public void eliminarCita(CitaMascota request) {

        try {
            Cita citaEncontrada = citaRepository.findById(request.getCita().getId_cita()).orElse(null);
            if(citaEncontrada!=null){
                citaEncontrada.setEstado(-1);
                citaRepository.saveAndFlush(citaEncontrada);
            }


        } catch (DataIntegrityViolationException e){
            System.out.println("error: "+ e.getMessage());
        }


    }


}
