package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.utp.edu.pe.model.Cita;
import com.utp.edu.pe.model.CitaMascota;

import com.utp.edu.pe.repository.CitaMascotaRepository;
import com.utp.edu.pe.request.PageableRequest;
import com.utp.edu.pe.service.CitaService;
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

import java.util.List;

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

//    @GetMapping(value = "/listarCitasPorDoctor/{id_doctor}")
//    public ResponseEntity<Doctor> listarCitasPorDoctor(@PathVariable("id_doctor") Integer id_doctor){
//        List<Citas> doctor = doctorService.buscarDoctor(id_doctor);
//        return (doctor != null) ? new ResponseEntity<>(doctor, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @PostMapping(value = "/listarCitasPorDoctor/{id_doctor}")
    public ResponseEntity<Page<CitaMascota>> listarCitasPorDoctor(
            @RequestBody PageableRequest request,
            @PathVariable("id_doctor") Integer id_doctor) {
        Page<CitaMascota> citas = null;
        Pageable pageable = null;
        Sort.Direction asc = null;

        String tipoOrden = request.getTypeOrder();
        asc = Sort.Direction.valueOf(tipoOrden);

        try{
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));
            citas = citaService.listarCitasPorDoctor(id_doctor,pageable);
            return (citas != null) ? new ResponseEntity<>(citas, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println("Message: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping(value = "/listarCitasPorCliente/{id_cliente}")
    public ResponseEntity<Page<CitaMascota>> listarCitasPorCliente(
            @RequestBody PageableRequest request,
            @PathVariable("id_cliente") Integer id_cliente) {
        Page<CitaMascota> citas = null;
        Pageable pageable = null;
        Sort.Direction asc = null;

        String tipoOrden = request.getTypeOrder();
        asc = Sort.Direction.valueOf(tipoOrden);

        try{
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));
            citas = citaService.listarCitasPorCliente(id_cliente,pageable);
            return (citas != null) ? new ResponseEntity<>(citas, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            System.out.println("Message: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping(value = "/cancelarCita/{id_cita}")
    public ResponseEntity<Void> cancelarCita(
            @PathVariable("id_cita") Integer id_cita) {
        Cita cita = citaService.cancelarCita(id_cita);
        return (cita != null) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping(value = "/terminarCita/{id_cita}")
    public ResponseEntity<Void> terminarCita(
            @PathVariable("id_cita") Integer id_cita) {
        Cita cita = citaService.terminarCita(id_cita);

        return (cita != null) ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @Autowired
    CitaMascotaRepository repository;
    @GetMapping(value = "/lista")
    public ResponseEntity<List<CitaMascota>> listaCliente(){
        List<CitaMascota> lista =   repository.findAll();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

}
