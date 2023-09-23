package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Integer> {
}
