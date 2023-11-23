package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.bean.HeaderRequest;
import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Especialidad;
import com.utp.edu.pe.service.DoctorService;
import com.utp.edu.pe.service.EspecialidadService;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.utp.edu.pe.util.ParametroValid.*;
import static com.utp.edu.pe.util.PetLifeUtil.*;

@RestController
@RequestMapping(Constantes.BASEPATH+Constantes.PATH_DOCTOR)
public class DoctorResource {

    private static final Logger LOG = LoggerFactory.getLogger(DoctorResource.class);

    @Autowired
    private PropertiesInterno propertiesInterno;

    @Autowired
    private DoctorService doctorService;

    @PostMapping(value = Constantes.PATH_REGISTRAR, consumes = "application/json", produces = "application/json")
    public ResponseEntity<BodyResponse> registrarDoctor(
            @RequestParam("doctor")  String doctor,
            @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException {

        BodyResponse response = null;
        String responsePrint = null;

        ObjectMapper objectMapper = new ObjectMapper();
        Doctor request = objectMapper.readValue(doctor, Doctor.class);

        System.out.print(responsePrint);

        try {

            String validParam = validarRegistrarDoctor(request);


            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){

                response = doctorService.registrarDoctor( request,imagen);
            }else {
                LOG.info(Constantes.VALIDACIONPARAMETROSINCORRECTOS);
                response = new BodyResponse();
                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO,validParam));

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            LOG.error( Constantes.MENSAJERROR + e.toString(), e);
            response = new BodyResponse();

            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE,e.toString()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {

            responsePrint = printPrettyJSONString(response);

            LOG.info( Constantes.PARAMETROS_SALIDA + Constantes.SALTO_LINEA + responsePrint);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(value = "actualizar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BodyResponse> actualizarDoctor(
            @RequestParam("doctor")  String doctor,
            @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException {

        BodyResponse response = null;
        String responsePrint = null;

        ObjectMapper objectMapper = new ObjectMapper();
        Doctor request = objectMapper.readValue(doctor, Doctor.class);

        System.out.print(responsePrint);

        try {

            String validParam = validarRegistrarDoctor(request);


            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){

                response = doctorService.actualizarDoctor( request,imagen);
            }else {
                LOG.info(Constantes.VALIDACIONPARAMETROSINCORRECTOS);
                response = new BodyResponse();
                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO,validParam));

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            LOG.error( Constantes.MENSAJERROR + e.toString(), e);
            response = new BodyResponse();

            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE,e.toString()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {

            responsePrint = printPrettyJSONString(response);

            LOG.info( Constantes.PARAMETROS_SALIDA + Constantes.SALTO_LINEA + responsePrint);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @Autowired
    EspecialidadService especialidadService;
    @GetMapping(value = "/lista-especialidades")
    public ResponseEntity<List<Especialidad>> listaEspecialidades(){
        List<Especialidad> lista =   especialidadService.listaEspecialidades();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }


}
