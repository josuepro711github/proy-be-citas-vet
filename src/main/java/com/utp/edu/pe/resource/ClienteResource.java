package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.bean.HeaderRequest;
import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Mascota;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.utp.edu.pe.util.ParametroValid.validarActualizarCliente;
import static com.utp.edu.pe.util.ParametroValid.validarRegistrarCliente;

import static com.utp.edu.pe.util.PetLifeUtil.printPrettyJSONString;
import static com.utp.edu.pe.util.PetLifeUtil.setHeaders;

@RestController
@RequestMapping(Constantes.BASEPATH + Constantes.PATH_CLIENTE)
public class ClienteResource {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteResource.class);

    @Autowired
    private PropertiesInterno propertiesInterno;

    @Autowired
    private ClienteService clienterService;

    @PostMapping(value = Constantes.PATH_REGISTRAR_CLIENTE)
    public ResponseEntity<BodyResponse> registrarCliente(
            @RequestParam("cliente") String cliente,
            @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException {

        BodyResponse response = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente request = objectMapper.readValue(cliente, Cliente.class);

            String validParam = validarRegistrarCliente(request);

            if (Constantes.CADENA_CERO.equalsIgnoreCase(validParam)) {

                response = clienterService.registrarCliente(request, imagen);

            } else {
                response = new BodyResponse();

                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO, validParam));

                return new ResponseEntity<BodyResponse>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {

            response = new BodyResponse();

            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE, e.toString()));

            return new ResponseEntity<BodyResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = Constantes.PATH_ACTUALIZAR_CLIENTE)
    public ResponseEntity<BodyResponse> actualizarCliente(
            @RequestParam("cliente") String cliente,
            @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException {

        BodyResponse response = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente request = objectMapper.readValue(cliente, Cliente.class);
            System.out.println("Cliente: " + cliente);

            String validParam = validarActualizarCliente(request);

            if (Constantes.CADENA_CERO.equalsIgnoreCase(validParam)) {

                response = clienterService.actualizarCliente(request, imagen);

            } else {
                response = new BodyResponse();

                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO, validParam));

                return new ResponseEntity<BodyResponse>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {

            response = new BodyResponse();

            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE, e.toString()));

            return new ResponseEntity<BodyResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = Constantes.PATH_ACTUALIZAR_CLIENTE)
    public ResponseEntity<BodyResponse> actualizarCliente(@RequestParam("cliente")  String cliente, @RequestParam("imagen") MultipartFile imagen)
        throws JsonProcessingException {

        BodyResponse response = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Cliente request = objectMapper.readValue(cliente, Cliente.class);

            String validParam = validarRegistrarCliente(request);

            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){

                response = clienterService.actualizarCliente( request,imagen);

            }else {
                response = new BodyResponse();

                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO,validParam));

                return new ResponseEntity<BodyResponse>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response = new BodyResponse();

            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE,e.toString()));

            return new ResponseEntity<BodyResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/pruebaCliente")
    public ResponseEntity<BodyResponse> pruebaCliente(
            @RequestParam("cliente") String cliente) throws JsonProcessingException {

        BodyResponse response = null;

        System.out.println(cliente);

        return ResponseEntity.ok(response);
    }

    @Autowired
    ClienteRepository repository;

    @GetMapping(value = "/lista")
    public ResponseEntity<List<Cliente>> listaCliente() {
        List<Cliente> lista = repository.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
