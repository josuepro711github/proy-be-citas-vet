package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.CitaMascota;
import com.utp.edu.pe.model.Cliente;

import com.utp.edu.pe.repository.CitaMascotaRepository;
import com.utp.edu.pe.request.PageableRequest;
import com.utp.edu.pe.service.CitaService;
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

import static com.utp.edu.pe.util.ParametroValid.validarRegistrarCliente;

@RestController
@RequestMapping(Constantes.BASEPATH+Constantes.PATH_CITA)
public class CitaMascotaResource {

    private static final Logger LOG = LoggerFactory.getLogger(CitaMascotaResource.class);

    @Autowired
    private PropertiesInterno propertiesInterno;

    @Autowired
    private CitaService citaService;

    @PostMapping(value = Constantes.PATH_REGISTRAR_CITA, consumes = "application/json", produces = "application/json")
    public ResponseEntity<CitaMascota> registrarCitaMascota(
            @RequestBody CitaMascota request)  throws JsonProcessingException {


        CitaMascota citaMascotaRegistrada = citaService.registrarCita(request);
        if(citaMascotaRegistrada!= null){
            return ResponseEntity.ok(citaMascotaRegistrada);
        }
        return new ResponseEntity<CitaMascota>(citaMascotaRegistrada, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(value = Constantes.PATH_ACTUALIZAR_CITA)
    public ResponseEntity<CitaMascota> actualizarCita(
            @RequestBody CitaMascota request
    )  throws JsonProcessingException {

        CitaMascota citaMascotaRegistrada = citaService.actualizarCita(request);
        if(citaMascotaRegistrada!= null){
            return ResponseEntity.ok(citaMascotaRegistrada);
        }
        return new ResponseEntity<CitaMascota>(citaMascotaRegistrada, HttpStatus.INTERNAL_SERVER_ERROR);


    }


    @DeleteMapping(value = Constantes.PATH_ELIMINAR_CITA, consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> eliminarCita(
            @RequestBody CitaMascota request)  throws JsonProcessingException {
        citaService.eliminarCita(request);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @Autowired
    CitaMascotaRepository repository;
    @GetMapping(value = "/lista")
    public ResponseEntity<List<CitaMascota>> listaCliente(){
        List<CitaMascota> lista =   repository.findAll();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

}
