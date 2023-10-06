package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Mascota;
import com.utp.edu.pe.service.MascotaService;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.utp.edu.pe.util.ParametroValid.*;

@RestController
@RequestMapping(Constantes.BASEPATH+Constantes.PATH_MASCOTA)
public class MascotaResource {

    private static final Logger LOG = LoggerFactory.getLogger(MascotaResource.class);

    @Autowired
    private PropertiesInterno propertiesInterno;

    @Autowired
    private MascotaService mascotaService;

    @PostMapping(value = Constantes.PATH_REGISTRAR_MASCOTA)
    public ResponseEntity<BodyResponse> registrarMascota(@RequestParam("mascota")  String mascota,
                                                         @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Mascota mascotaObj = objectMapper.readValue(mascota, Mascota.class);
        BodyResponse response = null;

        try {

            String validParam = validarRegistrarMascota(mascotaObj);

            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){
                response = mascotaService.registrarMascota( mascotaObj,imagen);
            }else {
                response = new BodyResponse();
                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO,validParam));

                return new ResponseEntity<BodyResponse>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            response = new BodyResponse();

            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE,e.toString()));

            return new ResponseEntity<BodyResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);


    }


}
