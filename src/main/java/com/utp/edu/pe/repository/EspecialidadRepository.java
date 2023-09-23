package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad,Integer> {
    Especialidad findByDescripcion(String descripcion);
}
