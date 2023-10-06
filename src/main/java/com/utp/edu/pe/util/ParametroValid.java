package com.utp.edu.pe.util;

import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Mascota;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.utp.edu.pe.exception.ConvertException;
import com.utp.edu.pe.model.Doctor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametroValid {

    private static final Logger LOG = LoggerFactory.getLogger(ParametroValid.class);

    public static String validarRegistrarDoctor(Doctor request) throws ConvertException {
        String validCodeRegistrarDoctor = Constantes.CADENA_CERO;

        try {
            if (null == request.getNombre() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getNombre().trim())) {
                validCodeRegistrarDoctor = "nombre";
                System.out.println("nombre");
                throw new ConvertException(validCodeRegistrarDoctor);
            }
            //Validar Usuario
            if ((null == request.getUsuario().getEmail() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getUsuario().getEmail().trim()))
                    || (!validarEmail(request.getUsuario().getEmail()))) {

                System.out.println("email");
                validCodeRegistrarDoctor = "email";
                throw new ConvertException(validCodeRegistrarDoctor);
            }
        } catch (ConvertException c) {
            LOG.error(Constantes.ERROR_FLAG, c);
            validCodeRegistrarDoctor = c.getMessage();
            return validCodeRegistrarDoctor;
        }
        return validCodeRegistrarDoctor;
    }

    public static String validarRegistrarCliente(Cliente request) throws ConvertException {
        String validCodeRegistrarDoctor = Constantes.CADENA_CERO;

        try {
            if (null == request.getNombre() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getNombre().trim())) {
                validCodeRegistrarDoctor = "nombre";
                System.out.println("nombre");
                throw new ConvertException(validCodeRegistrarDoctor);
            }
            //Validar Usuario
            if ((null == request.getUsuario().getEmail() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getUsuario().getEmail().trim()))
                    || (!validarEmail(request.getUsuario().getEmail()))) {

                System.out.println("email");
                validCodeRegistrarDoctor = "email";
                throw new ConvertException(validCodeRegistrarDoctor);
            }
        } catch (ConvertException c) {
            LOG.error(Constantes.ERROR_FLAG, c);
            validCodeRegistrarDoctor = c.getMessage();
            return validCodeRegistrarDoctor;
        }
        return validCodeRegistrarDoctor;
    }

    public static String validarRegistrarMascota(Mascota request) throws ConvertException {
        String validMascota = Constantes.CADENA_CERO;
        String validFecha = validarFecha(request);
        try {
            if (!validFecha.equals(Constantes.TEXTO_VACIO)) {
                validMascota = "fechaNacimiento";
                throw new ConvertException(validMascota + Constantes.GUION + validFecha);
            }
            if (null == request.getCliente().getId_cliente()) {
                validMascota = "idCliente";
                throw new ConvertException(validMascota);
            }
            if (null == request.getRaza().getId_raza()) {
                validMascota = "idRaza";
                throw new ConvertException(validMascota);
            }
            if (null == request.getRaza().getTipo_mascota().getId_tipo_mascota()) {
                validMascota = "idTipoMascota";
                throw new ConvertException(validMascota);
            }

        } catch (ConvertException c) {
            LOG.error(Constantes.ERROR_FLAG, c);
            validMascota = c.getMessage();
            return validMascota;
        }
        return validMascota;
    }

    private static String validarFecha(Mascota request) {

        String valid = "";
        if (request.getFecha_nacimiento() == null) {
            return Constantes.ES_NULO;
        }

        // Expresión regular para validar el formato de fecha
        String regex = "\\d{4}-\\d{2}-\\d{2}";

        // Verifica si el string coincide con el formato de fecha
        if (Pattern.matches(regex, request.getFecha_nacimiento())) {

            // El formato es correcto, ahora verifica si la fecha es válida
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA);
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(request.getFecha_nacimiento());

            } catch (ParseException e) {
                valid = e.getMessage();
            }
        }
        return valid;
    }

    public static Boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(Constantes.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}



