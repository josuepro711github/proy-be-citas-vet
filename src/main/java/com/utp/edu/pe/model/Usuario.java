package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    @Email(message = "Email incorrecto")
    private String email;
    private String contrasenia;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String dni;
    private String fecha_nacimiento;
    private String telefono;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_rol",referencedColumnName = "id_rol")
    private Rol rol;

}
