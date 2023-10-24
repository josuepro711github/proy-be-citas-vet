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
    private Especie id_especie;
}
