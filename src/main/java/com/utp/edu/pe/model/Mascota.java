package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private String alias;

    @NotNull
    private String genero;

    @NotNull
    private String fecha_nacimiento;

    @NotNull
    private String imagen;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_raza",referencedColumnName = "id_raza")
    private Raza raza;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente",referencedColumnName = "id_cliente")
    private Cliente cliente;


}
