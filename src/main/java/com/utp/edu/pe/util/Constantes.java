package com.utp.edu.pe.util;

public class Constantes {
    //Header
    public static final String IDTRANSACCION = "idTransaccion";
    public static final String MSGID = "msgid";
    public static final String TIMESTAMP = "timestamp";
    public static final String USRID = "userId";
    public static final String ACCEPT = "accept";
    public static final String APLICACION = "aplicacion";

    //Otros
    public static final int NUM_ZERO = 0;
    public static final String TEXTO_VACIO = "";
    public static final String ES_NULO = "es nulo";
    public static final String FORMATO_FECHA = "yyyy-MM-dd";
    public static final String GUION = " - ";
    public static final String TAG_MENSAJE = "$mensaje";
    public static final String FORMATO_FECHA_CABECERA = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String CADENA_CERO = "0";
    public static final String ERROR_FLAG = "Error validando el campo: ";
    public static final String TAG_PARAMETRO = "$parametro";
    public static final String TAG_USUARIO = "$usuario";
    public static final String CHAR_CORCHETE_IZQUIERDO = " [";
    public static final String CHAR_CORCHETE_DERECHO = " ]";
    public static final String REGISTRAR_DOCTOR = "registrarDoctor";
    public static final String MSG_ID = " msgid=";
    public static final String ID_TXT = " idTx=";
    public static final String VALIDACIONPARAMETROSCORRECTOS = " Validacion correcta de parametros de entrada";
    public static final String VALIDACIONPARAMETROSINCORRECTOS = " Validacion incorrecta de parametros de entrada";
    public static final String MENSAJERROR = "[ERROR]: ";
    public static final String SALTO_LINEA = "\n";
    public static final String PARAMETROS_ENTRADA = "PARAMETROS DE ENTRADA: ";
    public static final String PARAMETROS_SALIDA = "PARAMETROS DE SALIDA: ";

    //Paths
    public static final String BASEPATH = "/api/vet/petlife/v1.0.0";

    public static final String PATH_DOCTOR = "/doctor";
    public static final String PATH_REGISTRAR = "/registrar";
    public static final String PATH_LISTAR = "/listar";

    public static final String PATH_CLIENTE = "/cliente";
    public static final String PATH_MASCOTA = "/mascota";

    public static final String PATH_REGISTRAR_CLIENTE = "/registrarCliente";

    public static final String PATH_REGISTRAR_MASCOTA = "/registrarMascota";

    //Roles
    public static final int ROL_DOCTOR = 2;
    public static final int ROL_CLIENTE = 3;

    public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

}
