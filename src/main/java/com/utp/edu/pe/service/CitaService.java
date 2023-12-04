package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.CitaMascota;

public interface CitaService {
    public CitaMascota registrarCita(CitaMascota request);
    public CitaMascota actualizarCita(CitaMascota request);
    public void eliminarCita(CitaMascota request);
}
