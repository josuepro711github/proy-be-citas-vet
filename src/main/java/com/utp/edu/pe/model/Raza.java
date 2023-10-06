package com.utp.edu.pe.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="raza")
public class Raza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_raza;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_mascota",referencedColumnName = "id_tipo_mascota")
    private TipoMascota tipo_mascota;
}
