package com.utp.edu.pe.model;

import jakarta.persistence.*;
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
    private String email;
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "id_rol",referencedColumnName = "id_rol")
    private Rol rol;

}
