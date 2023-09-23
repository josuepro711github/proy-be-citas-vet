package com.utp.edu.pe.model;

import javax.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="estado_cita")
@Entity
public class EstadoCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_estado_cita;
    private Date descripcion;
}
