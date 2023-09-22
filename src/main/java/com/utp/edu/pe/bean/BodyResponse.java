package com.utp.edu.pe.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BodyResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idTransaccion;
    private String codigoRespuesta;
    private String mensajeRespuesta;
}
