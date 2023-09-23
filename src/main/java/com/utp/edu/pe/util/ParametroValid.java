package com.utp.edu.pe.util;
import com.utp.edu.pe.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.utp.edu.pe.exception.ConvertException;
import com.utp.edu.pe.model.Doctor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParametroValid {

    private static final Logger LOG = LoggerFactory.getLogger(ParametroValid.class);

    public static String validarRegistrarDoctor(Doctor request) throws ConvertException {
        String validCodeRegistrarDoctor = Constantes.CADENA_CERO;

        try {
            if(null == request.getNombre() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getNombre().trim())){
                validCodeRegistrarDoctor = "nombre";
                System.out.println("nombre");
                throw new ConvertException(validCodeRegistrarDoctor);
            }
            //Validar Usuario
            if( (null == request.getUsuario().getEmail() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getUsuario().getEmail().trim()))
                    ||(!validarEmail(request.getUsuario().getEmail())) ){

                System.out.println("email");
                validCodeRegistrarDoctor = "email";
                throw new ConvertException(validCodeRegistrarDoctor);
            }
        }catch (ConvertException c){
            LOG.error(Constantes.ERROR_FLAG, c);
            validCodeRegistrarDoctor = c.getMessage();
            return validCodeRegistrarDoctor;
        }
        return validCodeRegistrarDoctor;
    }

    public static String validarRegistrarCliente(Cliente request) throws ConvertException {
        String validCodeRegistrarDoctor = Constantes.CADENA_CERO;

        try {
            if(null == request.getNombre() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getNombre().trim())){
                validCodeRegistrarDoctor = "nombre";
                System.out.println("nombre");
                throw new ConvertException(validCodeRegistrarDoctor);
            }
            //Validar Usuario
            if( (null == request.getUsuario().getEmail() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getUsuario().getEmail().trim()))
                    ||(!validarEmail(request.getUsuario().getEmail())) ){

                System.out.println("email");
                validCodeRegistrarDoctor = "email";
                throw new ConvertException(validCodeRegistrarDoctor);
            }
        }catch (ConvertException c){
            LOG.error(Constantes.ERROR_FLAG, c);
            validCodeRegistrarDoctor = c.getMessage();
            return validCodeRegistrarDoctor;
        }
        return validCodeRegistrarDoctor;
    }

    public static Boolean validarEmail(String email){
        Pattern pattern = Pattern.compile(Constantes.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
