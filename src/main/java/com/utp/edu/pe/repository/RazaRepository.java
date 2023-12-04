package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Especie;
import com.utp.edu.pe.model.Raza;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RazaRepository extends JpaRepository<Raza,Integer> {

    List<Raza> findByEspecie(Especie especie);
}
