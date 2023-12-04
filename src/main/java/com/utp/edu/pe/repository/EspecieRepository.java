package com.utp.edu.pe.repository;

import com.utp.edu.pe.model.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecieRepository extends JpaRepository<Especie,Integer> {

}
