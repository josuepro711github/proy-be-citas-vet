package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="rol")
@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rol;

    @NotNull
    private String tipo_rol;

}
