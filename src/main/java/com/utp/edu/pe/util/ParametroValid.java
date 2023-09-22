package com.utp.edu.pe.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.utp.edu.pe.exception.ConvertException;
import com.utp.edu.pe.model.Doctor;

public class ParametroValid {

    private static final Logger LOG = LoggerFactory.getLogger(ParametroValid.class);

    public static String validarRegistrarDoctor(Doctor request) throws ConvertException {
        String validCodeRegistrarDoctor = Constantes.CADENA_CERO;

        try {
            if(null == request.getNombre() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getNombre())){
                validCodeRegistrarDoctor = "nombre";
                throw new ConvertException(validCodeRegistrarDoctor);
            }
            //Validar Usuario
            if(null == request.getUsuario().getEmail() || Constantes.TEXTO_VACIO.equalsIgnoreCase(request.getUsuario().getEmail())){
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

}
