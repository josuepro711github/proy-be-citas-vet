package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Doctor;
import com.utp.edu.pe.model.Especialidad;
import com.utp.edu.pe.model.Usuario;
import com.utp.edu.pe.repository.DoctorRepository;
import com.utp.edu.pe.repository.EspecialidadRepository;
import com.utp.edu.pe.repository.UsuarioRepository;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private PropertiesInterno propertiesInterno;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ImagenService imagenService;
    @Override
    public BodyResponse registrarDoctor(Doctor request, MultipartFile imagen) {
        BodyResponse response = new BodyResponse();

        try {
            Usuario existeUsuario = usuarioRepository.findByEmail(request.getUsuario().getEmail());
            if(null!=existeUsuario){
                response.setCodigoRespuesta(propertiesInterno.idf2Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf2Mensaje.replace(Constantes.TAG_USUARIO, existeUsuario.getEmail()));
                return response;
            }
            request.getUsuario().setContrasenia(new BCryptPasswordEncoder().encode(request.getUsuario().getContrasenia()));
            request.getUsuario().getRol().setId_rol(Constantes.ROL_DOCTOR);

//            Especialidad existeEspecialidad = especialidadRepository.findByDescripcion(request.getEspecialidad().getDescripcion());
//            if(null==existeEspecialidad){
//                Especialidad especialidadGuardada = especialidadRepository.save(request.getEspecialidad());
//                request.setEspecialidad(especialidadGuardada);
//            }else{
//                request.setEspecialidad(existeEspecialidad);
//            }

            String nombreImagen = imagenService.cargarImagen(imagen,"doctores");
            request.getUsuario().setImagen(nombreImagen);

            Usuario usuarioGuardado = usuarioRepository.save(request.getUsuario());
            request.setUsuario(usuarioGuardado);

            doctorRepository.save(request);
            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        } catch (DataIntegrityViolationException e){
            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));
        }

        return response;
    }

    @Override
    public BodyResponse actualizarDoctor(Doctor request, MultipartFile imagen) {
        BodyResponse response = new BodyResponse();

        try {

            Doctor doctorEncontrado =doctorRepository.findById(request.getId_doctor()).orElse(null);
            request.getUsuario().setContrasenia(doctorEncontrado.getUsuario().getContrasenia());
//            Especialidad existeEspecialidad = especialidadRepository.findByDescripcion(request.getEspecialidad().getDescripcion());
//            if(null==existeEspecialidad){
//                Especialidad especialidadGuardada = especialidadRepository.save(request.getEspecialidad());
//                request.setEspecialidad(especialidadGuardada);
//            }else{
//                request.setEspecialidad(existeEspecialidad);
//            }
            if(request.getUsuario().getImagen().equals("cambiado")){
                imagenService.eliminarImagen(doctorEncontrado.getUsuario().getImagen(),"doctores");
                String nombreImagen = imagenService.cargarImagen(imagen,"doctores");
                request.getUsuario().setImagen(nombreImagen);
            }
            System.out.println("Request "+ request.getUsuario());
            Usuario usuarioGuardado = usuarioRepository.saveAndFlush(request.getUsuario());
            request.setUsuario(usuarioGuardado);

            doctorRepository.saveAndFlush(request);
            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        } catch (DataIntegrityViolationException e){
            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));
        }

        return response;
    }

    @Override
    public Page<Doctor> listarDoctor(Pageable pageable) {

        Page<Doctor> listaDoctor = doctorRepository.findAll(pageable);
        return listaDoctor;
    }

    @Override
    public Doctor buscarDoctor(Integer id) {
        Doctor buscarDoctor = doctorRepository.findById(id).orElse(null);
        if(buscarDoctor != null){
            buscarDoctor.getUsuario().setContrasenia("");
        }
        return doctorRepository.findById(id).orElse(null);
    }


}
