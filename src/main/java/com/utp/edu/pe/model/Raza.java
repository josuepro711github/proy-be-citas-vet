package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="raza")
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_raza;

    @NotNull
    private String descripcion;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_especie",referencedColumnName = "id_especie")
    private Especie especie;

    public Integer getId_raza() {
        return id_raza;
    }

    public void setId_raza(Integer id_raza) {
        this.id_raza = id_raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
}
