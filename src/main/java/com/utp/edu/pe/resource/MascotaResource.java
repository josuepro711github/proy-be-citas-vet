package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.*;
import com.utp.edu.pe.repository.ClienteRepository;
import com.utp.edu.pe.repository.MascotaRepository;
import com.utp.edu.pe.request.PageableRequest;
import com.utp.edu.pe.service.EspecialidadService;
import com.utp.edu.pe.service.MascotaService;
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


        BodyResponse response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        Mascota mascotaObj = objectMapper.readValue(mascota, Mascota.class);
        response = mascotaService.registrarMascota( mascotaObj,imagen);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = Constantes.PATH_ACTUALIZAR_MASCOTA)
    public ResponseEntity<BodyResponse> actualizarMascota(@RequestParam("mascota")  String mascota,
                                                         @RequestParam("imagen") MultipartFile imagen) throws JsonProcessingException {


        BodyResponse response = null;
        ObjectMapper objectMapper = new ObjectMapper();
        Mascota mascotaObj = objectMapper.readValue(mascota, Mascota.class);
        response = mascotaService.actualizarMascota( mascotaObj,imagen);

        return ResponseEntity.ok(response);
    }


    @PostMapping(value = Constantes.PATH_LISTAR_MASCOTAS_POR_CLIENTE, consumes = "application/json", produces = "application/json")
    public Page<Mascota> listarMascotasPorCliente(@RequestBody PageableRequest request,  @PathVariable("id_cliente") Integer id_cliente) {
        Page<Mascota> mascotas = null;
        Pageable pageable = null;
        Sort.Direction asc = null;

        String tipoOrden = request.getTypeOrder().toUpperCase();
        asc = (tipoOrden.equals("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC;

        try{
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));
            mascotas = mascotaService.listarMascotasPorCliente(id_cliente,pageable);
        }catch (Exception e){
            System.out.println("Mensaje: " + e.getMessage());
        }
        return mascotas;
    }

    @DeleteMapping(value = "/eliminarMascota/{id_mascota}")
    public ResponseEntity<Mascota> eliminarMascota(@PathVariable("id_mascota") Integer id_mascota){
        Mascota lista =   mascotaService.eliminarMascota(id_mascota);
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }


    @GetMapping(value = "/listarRazasPorEspecie/{id_especie}")
    public ResponseEntity<List<Raza>> listarRazasPorEspecie(@PathVariable("id_especie") Integer id_especie){
        List<Raza> lista =   mascotaService.listarRazasPorEspecie(id_especie);
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @GetMapping(value = "/listarEspecies")
    public ResponseEntity<List<Especie>> listarEspecies(){
        List<Especie> lista =   mascotaService.listarEspecies();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

}
