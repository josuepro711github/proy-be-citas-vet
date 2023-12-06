package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Especialidad;
import com.utp.edu.pe.model.Mascota;
import com.utp.edu.pe.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findByUsuario(Usuario usuario);

    List<Doctor> findByEspecialidad(Especialidad especialidad);
    Page<Doctor> findAll(Pageable pageable);
}
