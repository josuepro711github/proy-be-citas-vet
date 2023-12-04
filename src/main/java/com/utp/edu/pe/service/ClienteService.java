package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Cliente;
import org.springframework.web.multipart.MultipartFile;

public interface ClienteService {
    public BodyResponse registrarCliente(Cliente request, MultipartFile imagen);
    public BodyResponse actualizarCliente(Cliente request, MultipartFile imagen);
}
