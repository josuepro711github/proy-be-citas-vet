package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.CitaMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaMascotaRepository extends JpaRepository<CitaMascota,Integer> {

}
