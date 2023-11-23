package com.utp.edu.pe.service;

import com.utp.edu.pe.model.Especialidad;
import com.utp.edu.pe.repository.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{

    @Autowired
    EspecialidadRepository especialidadRepository;

    @Override
    public List<Especialidad> listaEspecialidades() {
        return especialidadRepository.findAll();
    }
}
