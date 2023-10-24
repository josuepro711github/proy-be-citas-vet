package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DoctorService {
    public BodyResponse registrarDoctor(String idTransaccion, Doctor request);

    public Page<Doctor> listarDoctor(Pageable pageable);
}
