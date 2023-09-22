package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Especialidad;

public interface DoctorService {
    public BodyResponse registrarDoctor(String idTransaccion, Doctor request);
}
