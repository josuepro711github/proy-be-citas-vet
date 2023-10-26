package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido_paterno;

    @NotNull
    private String apellido_materno;

    @NotNull
    private String dni;

    @NotNull
    private String fecha_nacimiento;

    @NotNull
    private String telefono;

    @NotNull
    private String imagen;

    @Email(message = "Email incorrecto")
    @NotNull
    private String email;

    @NotNull
    private String contrasenia;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String dni;
    private String fecha_nacimiento;
    private String telefono;
    private String imagen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;
}
