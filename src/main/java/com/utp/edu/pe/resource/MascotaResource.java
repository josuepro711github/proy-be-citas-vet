package com.utp.edu.pe.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Mascota;
import com.utp.edu.pe.request.PageableRequest;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            Mascota mascotaObj = objectMapper.readValue(mascota, Mascota.class);
//
//            String validParam = validarRegistrarMascota(mascotaObj);
//
//            if(Constantes.CADENA_CERO.equalsIgnoreCase(validParam)){
//
//
//            }else {
//                response = new BodyResponse();
//                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
//                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_PARAMETRO,validParam));
//
//                return new ResponseEntity<BodyResponse>(response, HttpStatus.BAD_REQUEST);
//            }
//
//        } catch (Exception e){
//            System.out.println(e+"\n" +e.getMessage());
//            response = new BodyResponse();
//
//            response.setCodigoRespuesta(propertiesInterno.idt3Codigo);
//            response.setMensajeRespuesta(propertiesInterno.idt3Mensaje.replace(Constantes.TAG_MENSAJE,e.getMessage()));
//
//            return new ResponseEntity<BodyResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        return ResponseEntity.ok(response);
    }


   @PostMapping(value = Constantes.PATH_LISTAR_MASCOTAS, consumes = "application/json", produces = "application/json")
    public Page<Mascota> listarMascotas(@RequestBody PageableRequest request) {
        Page<Mascota> mascotas = null;
        Pageable pageable = null;
        Sort.Direction asc = null;

        String tipoOrden = request.getTypeOrder().toUpperCase();
        asc = (tipoOrden.equals("ASC")) ? Sort.Direction.ASC : Sort.Direction.DESC;

        try{
            pageable = PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));
            mascotas = mascotaService.listarMascota(pageable);
        }catch (Exception e){

            System.out.println("Ocurrio una excepcion: " + e + e.getMessage());
            request.setOrderParameter("alias");

            pageable = e.getMessage().contains("No property 'id'")? PageRequest.of(request.getPage(), request.getSize() ):
                            PageRequest.of(request.getPage(), request.getSize(), Sort.by(asc, request.getOrderParameter()));

            mascotas = mascotaService.listarMascota(pageable);
        }
        return mascotas;
    }
}
