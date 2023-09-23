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

    @ManyToOne
    @JoinColumn(name = "id_rol",referencedColumnName = "id_rol")
    private Rol rol;

}
