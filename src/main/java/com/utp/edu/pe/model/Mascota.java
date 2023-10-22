package com.utp.edu.pe.model;

import javax.persistence.*;
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
    private Integer id_mascota;
    private String alias;
    private String genero;
    private String fecha_nacimiento;
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_tipo_mascota",referencedColumnName = "id_tipo_mascota")
    private TipoMascota tipo_mascota;

    @ManyToOne
    @JoinColumn(name = "id_cliente",referencedColumnName = "id_cliente")
    private Cliente cliente;


}
