package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Cliente;

public interface ClienteService {
    public BodyResponse registrarCliente(Cliente request);
}
