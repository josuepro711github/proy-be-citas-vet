package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    Cliente findByUsuario(Usuario usuario);
}
