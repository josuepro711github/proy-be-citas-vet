package com.utp.edu.pe.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="cita_mascota")
@Entity
public class CitaMascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cita_mascota;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_mascota",referencedColumnName = "id_mascota")
    private Mascota mascota;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cita",referencedColumnName = "id_cita")
    private Cita cita;


    public Integer getId_cita_mascota() {
        return id_cita_mascota;
    }

    public void setId_cita_mascota(Integer id_cita_mascota) {
        this.id_cita_mascota = id_cita_mascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
