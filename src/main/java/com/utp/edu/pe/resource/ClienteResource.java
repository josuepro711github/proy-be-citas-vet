package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.bean.HeaderRequest;
import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.repository.ClienteRepository;
import com.utp.edu.pe.service.ClienteService;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.utp.edu.pe.util.ParametroValid.validarRegistrarCliente;

import static com.utp.edu.pe.util.PetLifeUtil.printPrettyJSONString;
import static com.utp.edu.pe.util.PetLifeUtil.setHeaders;

@RestController
@RequestMapping(Constantes.BASEPATH+Constantes.PATH_CLIENTE)
public class ClienteResource {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteResource.class);

    @Autowired
    private PropertiesInterno propertiesInterno;

    @Autowired
    private ClienteService clienterService;

    @PostMapping(value = Constantes.PATH_REGISTRAR_CLIENTE, consumes = "application/json", produces = "application/json")
    public ResponseEntity<BodyResponse> registrarCliente(
            @RequestBody Cliente request)  {

        BodyResponse response = null;

        try {

            String validParam = validarRegistrarCliente(request);

            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){

                response = clienterService.registrarCliente( request);

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


    @Autowired
    ClienteRepository repository;
    @GetMapping(value = "/lista")
    public ResponseEntity<List<Cliente>> listaCliente(){
        List<Cliente> lista =   repository.findAll();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

}
