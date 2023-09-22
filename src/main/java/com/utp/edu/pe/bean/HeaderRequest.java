package com.utp.edu.pe.bean;

import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PetLifeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Calendar;
import java.util.Date;
import javax.ws.rs.core.HttpHeaders;
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class HeaderRequest {
    private String idTransaccion;
    private String msgid;
    private Date timestamp;
    private String userId;
    private String accept;
    private String aplicacion;

    public HeaderRequest(HttpHeaders httpHeaders) {
        super();

        this.idTransaccion = httpHeaders.getRequestHeader(Constantes.IDTRANSACCION) != null
                ? httpHeaders.getRequestHeader(Constantes.IDTRANSACCION).get(Constantes.NUM_ZERO)
                : null;
        this.msgid = httpHeaders.getRequestHeader(Constantes.MSGID) != null
                ? httpHeaders.getRequestHeader(Constantes.MSGID).get(Constantes.NUM_ZERO)
                : null;
        this.aplicacion = httpHeaders.getRequestHeader(Constantes.APLICACION) != null
                ? httpHeaders.getRequestHeader(Constantes.APLICACION).get(Constantes.NUM_ZERO)
                : null;
        this.userId = httpHeaders.getRequestHeader(Constantes.USRID) != null
                ? httpHeaders.getRequestHeader(Constantes.USRID).get(Constantes.NUM_ZERO)
                : null;
        this.accept = httpHeaders.getRequestHeader(Constantes.ACCEPT) != null
                ? httpHeaders.getRequestHeader(Constantes.ACCEPT).get(Constantes.NUM_ZERO)
                : null;
        Calendar a = PetLifeUtil.toCalendar(httpHeaders.getRequestHeader(Constantes.TIMESTAMP) != null
                ? httpHeaders.getRequestHeader(Constantes.TIMESTAMP).get(Constantes.NUM_ZERO)
                : Constantes.TEXTO_VACIO);
        this.timestamp = new Date();
        if (a != null) {
            this.timestamp = a.getTime();

        }
    }

}
