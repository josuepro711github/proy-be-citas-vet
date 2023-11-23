package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.bean.HeaderRequest;
import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Mascota;
import com.utp.edu.pe.request.PageableRequest;
import com.utp.edu.pe.service.DoctorService;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestHeader(name = "idTransaccion", required = false) String idTransaccion,
			@RequestHeader(name = "userId", required = false) String userId,
            @RequestHeader(name = "msgid", required = false) String msgid,
            @RequestHeader(name = "timestamp", required = false) String timestamp,
            @RequestHeader(name = "accept", required = false) String accept,
            @RequestHeader(name = "aplicacion", required = false) String aplicacion,
            @RequestBody(required = true) Doctor request) throws JsonProcessingException {

        BodyResponse response = null;
        String requestPrint = null;
        String responsePrint = null;
        HeaderRequest headerRequest = new HeaderRequest();

        setHeaders(headerRequest, idTransaccion, userId, msgid, accept, aplicacion, timestamp);
        String msjTx = Constantes.CHAR_CORCHETE_IZQUIERDO + Constantes.REGISTRAR_DOCTOR + Constantes.ID_TXT + idTransaccion
                + Constantes.MSG_ID + headerRequest.getMsgid() + Constantes.CHAR_CORCHETE_DERECHO;


        System.out.print(responsePrint);

        try {

            String validParam = validarRegistrarDoctor(request);
            requestPrint = printPrettyJSONString(request);

            LOG.info(msjTx + Constantes.PARAMETROS_ENTRADA + Constantes.SALTO_LINEA + requestPrint);

            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){
                LOG.info(msjTx + Constantes.VALIDACIONPARAMETROSCORRECTOS);
                response = doctorService.registrarDoctor(idTransaccion, request);
            }else {
                LOG.info(msjTx + Constantes.VALIDACIONPARAMETROSINCORRECTOS);
                response = new BodyResponse();
                response.setIdTransaccion(idTransaccion);
                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO,validParam));

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e){
            LOG.error(msjTx + Constantes.MENSAJERROR + e.toString(), e);
            response = new BodyResponse();
            response.setIdTransaccion(idTransaccion);
            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE,e.toString()));

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {

            responsePrint = printPrettyJSONString(response);

            LOG.info(msjTx + Constantes.PARAMETROS_SALIDA + Constantes.SALTO_LINEA + responsePrint);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(value = Constantes.PATH_LISTAR_DOCTORES, consumes = "application/json", produces = "application/json")
    public Page<Doctor> listarDoctores(@RequestBody PageableRequest request) {
        Page<Doctor> mascotas = null;
        Pageable pageable = null;
        Sort.Direction asc = null;

        String tipoOrden = request.getTypeOrder().toUpperCase();
        asc = (tipoOrden.equals("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC;

        try{
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));
            mascotas = doctorService.listarDoctor(pageable);
        }catch (Exception e){

            request.setOrderParameter("id_usuario");

            pageable = e.getMessage().contains("No property 'id'")? PageRequest.of(request.getPage(), request.getSize() ):
                    PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));

            mascotas = doctorService.listarDoctor(pageable);
        }
        return mascotas;
    }

}
