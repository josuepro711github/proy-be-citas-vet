package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Integer> {

}
