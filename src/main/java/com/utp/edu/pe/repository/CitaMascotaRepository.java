package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaMascotaRepository extends JpaRepository<CitaMascota,Integer> {

    Page<CitaMascota> findByCitaDoctor(Doctor doctor, Pageable pageable);
    Page<CitaMascota> findByMascotaCliente(Cliente cliente, Pageable pageable);
}
