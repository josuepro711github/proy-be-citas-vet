package com.utp.edu.pe.model;

import jakarta.persistence.*;
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
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String dni;
    private String fecha_nacimiento;
    private String telefono;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_especialidad",referencedColumnName = "id_especialidad")
    private Especialidad especialidad;
}
