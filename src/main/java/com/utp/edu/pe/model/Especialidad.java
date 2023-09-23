package com.utp.edu.pe.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="especialidad")
@Entity
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_especialidad;
    private String descripcion;

}
