package com.utp.edu.pe.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.utp.edu.pe.model.Usuario;
import com.utp.edu.pe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static com.utp.edu.pe.util.ParametroValid.validarEmail;

@Component
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException>  {

    @Autowired
    UsuarioRepository usuarioRepository;

    public CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();

        AuthenticationFailureBadCredentialsEvent event = value.getEvent();
        String username = (String) event.getAuthentication().getPrincipal();
        Usuario existeUsuario = usuarioRepository.findByEmail(username);

        String tipoFallo = "Contraseña Incorrecta";

        if(!validarEmail(username)){
            tipoFallo = "Email no Válido";
        }else if(existeUsuario==null){
            tipoFallo = "El Usuario no Existe";
        }


        jsonGenerator.writeNumberField("code", value.getHttpErrorCode());
        jsonGenerator.writeBooleanField("status", false);
        jsonGenerator.writeObjectField("respuesta", tipoFallo);
        jsonGenerator.writeObjectField("recomendacion", username+" ve por tu gatita ");
        jsonGenerator.writeObjectField("errors", Arrays.asList(value.getOAuth2ErrorCode(),value.getMessage()));
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }


}