package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="cita")
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cita;

    @NotNull
    private Date fecha;

    @NotNull
    private String motivo;

    @NotNull
    private String hora_cita;

    @NotNull
    private String observaciones;

    @NotNull
    private String estado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_doctor",referencedColumnName = "id_doctor")
    private Doctor doctor;
}
