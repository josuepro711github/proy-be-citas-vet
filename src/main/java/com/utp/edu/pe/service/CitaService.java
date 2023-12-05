package com.utp.edu.pe.service;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.CitaMascota;
import com.utp.edu.pe.repository.CitaMascotaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CitaService {
    public CitaMascota registrarCita(CitaMascota request);
    public CitaMascota actualizarCita(CitaMascota request);
    public void eliminarCita(CitaMascota request);


    public Page<CitaMascota>  listarCitasPorDoctor(Integer id_doctor, Pageable pageable);

    public Page<CitaMascota>  listarCitasPorCliente(Integer id_cliente, Pageable pageable);

    public Cita cancelarCita(Integer id_cita);
    public Cita terminarCita(Integer id_cita);

}
