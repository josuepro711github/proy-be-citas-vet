package com.utp.edu.pe.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="tipo_mascota")
@Entity
public class TipoMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tipo_mascota;

    @ManyToOne
    @JoinColumn(name = "id_raza",referencedColumnName = "id_raza")
    private Raza raza;

    private String descripcion;
}
