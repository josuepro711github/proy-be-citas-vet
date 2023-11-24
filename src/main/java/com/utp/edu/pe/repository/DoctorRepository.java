package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Mascota;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Page<Doctor> findAll(Pageable pageable);
}
