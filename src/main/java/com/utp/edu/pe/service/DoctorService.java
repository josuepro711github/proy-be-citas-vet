package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DoctorService {
    public BodyResponse registrarDoctor(Doctor request, MultipartFile imagen);

    public BodyResponse actualizarDoctor(Doctor request, MultipartFile imagen);

    public Page<Doctor> listarDoctor(Pageable pageable);
    public Doctor buscarDoctor(Integer id);

    public Doctor eliminarDoctor(Integer id);

    List<Doctor> listaDoctoresPorEspecialidad(Especialidad especialidad);

}
