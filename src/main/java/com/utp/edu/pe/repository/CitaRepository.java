package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Cita;
import com.utp.edu.pe.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita,Integer> {



}
