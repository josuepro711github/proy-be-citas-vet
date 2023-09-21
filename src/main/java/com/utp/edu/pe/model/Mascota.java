package com.utp.edu.pe.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="mascota")
@Entity
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    private String alias;
    private String genero;
    private String fecha_nacimiento;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_raza",referencedColumnName = "id_raza")
    private Raza raza;

    @ManyToOne
    @JoinColumn(name = "id_cliente",referencedColumnName = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_tipo_mascota",referencedColumnName = "id_tipo_mascota")
    private TipoMascota tipoMascota;
}
