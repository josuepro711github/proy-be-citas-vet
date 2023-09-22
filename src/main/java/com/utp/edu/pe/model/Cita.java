package com.utp.edu.pe.model;

import javax.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="cita")
@Entity
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cita;
    private Date fecha;
    private String motivo;
    private String hora_cita;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_estado_cita",referencedColumnName = "id_estado_cita")
    private EstadoCita estadoCita;

    @ManyToOne
    @JoinColumn(name = "id_doctor",referencedColumnName = "id_doctor")
    private Doctor doctor;
}
