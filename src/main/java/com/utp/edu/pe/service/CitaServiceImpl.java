package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.*;
import com.utp.edu.pe.repository.*;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

            if(citaEncontrada==null){
                return null;
            }

            Cita citaRegistrada = citaRepository.saveAndFlush(request.getCita());
            request.setCita(citaRegistrada);

            response = request;

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

    @Override
    public Page<CitaMascota> listarCitasPorDoctor(Integer id_doctor,Pageable pageable) {

        Doctor doctor = new Doctor();
        doctor.setId_doctor(id_doctor);

        Page<CitaMascota> listaCita = citaMascotaRepository.findByCitaDoctor(doctor,pageable);
        if(listaCita.isEmpty()){
            return null;
        }

        return listaCita;
    }

    @Override
    public Page<CitaMascota> listarCitasPorCliente(Integer id_cliente, Pageable pageable) {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(id_cliente);

        Page<CitaMascota> listaCita = citaMascotaRepository.findByMascotaCliente(cliente,pageable);

        if(listaCita.isEmpty()){
            return null;
        }

        return listaCita;
    }

    @Override
    public Cita cancelarCita(Integer id_cita) {
        Cita cita = citaRepository.findById(id_cita).orElse(null);
        if (cita != null){
            cita.setEstado(3);
            citaRepository.saveAndFlush(cita);
        }
        return cita;
    }

    @Override
    public Cita terminarCita(Integer id_cita) {
        Cita cita = citaRepository.findById(id_cita).orElse(null);
        if (cita != null){
            cita.setEstado(1);
            citaRepository.saveAndFlush(cita);
        }
        return cita;
    }


}
