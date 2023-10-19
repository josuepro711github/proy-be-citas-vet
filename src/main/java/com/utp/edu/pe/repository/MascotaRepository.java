package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Integer> {
}
