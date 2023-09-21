package com.utp.edu.pe.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="detalle_cita")
@Entity
public class DetalleCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_cita;

    @ManyToOne
    @JoinColumn(name = "id_mascota",referencedColumnName = "id_mascota")
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "id_cita",referencedColumnName = "id_cita")
    private Cita cita;
}
