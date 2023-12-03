package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


public interface MascotaService {
    public BodyResponse registrarMascota(Mascota request, MultipartFile imagen);

    public BodyResponse actualizarMascota(Mascota request, MultipartFile imagen);
    Page<Mascota> listarMascota(Pageable pageable);
}
