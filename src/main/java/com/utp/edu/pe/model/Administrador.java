package com.utp.edu.pe.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="administrador")
@Entity
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_administrador;
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

}
