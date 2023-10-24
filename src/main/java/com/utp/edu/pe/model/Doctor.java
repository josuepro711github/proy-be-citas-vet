package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="doctor")
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_doctor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_especialidad",referencedColumnName = "id_especialidad")
    private Especialidad especialidad;
}
