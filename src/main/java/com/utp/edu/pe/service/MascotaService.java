package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Especie;
import com.utp.edu.pe.model.Mascota;
import com.utp.edu.pe.model.Raza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface MascotaService {
    public BodyResponse registrarMascota(Mascota request, MultipartFile imagen);

    public BodyResponse actualizarMascota(Mascota request, MultipartFile imagen);
    Page<Mascota> listarMascotasPorCliente(Integer id_cliente,Pageable pageable);

    public List<Raza> listarRazasPorEspecie(Integer id_especie);
    public List<Especie> listarEspecies();
}
