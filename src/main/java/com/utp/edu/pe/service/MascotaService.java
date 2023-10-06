package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Mascota;
import org.springframework.web.multipart.MultipartFile;

public interface MascotaService {
    public BodyResponse registrarMascota(Mascota request, MultipartFile imagen);
}
