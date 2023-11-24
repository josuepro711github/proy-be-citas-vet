package com.utp.edu.pe.bean;

import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PetLifeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.ws.rs.core.HttpHeaders;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class HeaderRequest {
    private String idTransaccion;
    private String msgid;
    private String timestamp;
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
       // this.timestamp = new Date();
//        if (a != null) {
//            this.timestamp = a.getTime();
//
//        }
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }
}
