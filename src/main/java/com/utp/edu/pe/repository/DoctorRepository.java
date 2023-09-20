package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
