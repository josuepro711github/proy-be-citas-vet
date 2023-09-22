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
import org.springframework.stereotype.Service;

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

    @Override
    public BodyResponse registrarDoctor(String idTransaccion, Doctor request) {
        BodyResponse response = new BodyResponse();
        Usuario existeUsuario = usuarioRepository.findByEmail(request.getUsuario().getEmail());
        if(null!=existeUsuario){
            response.setIdTransaccion(idTransaccion);
            response.setCodigoRespuesta(propertiesInterno.idf2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf2Mensaje.replace(Constantes.TAG_USUARIO, existeUsuario.getEmail()));
            return response;
        }

        request.getUsuario().getRol().setId_rol(Constantes.ROL_DOCTOR);

        Especialidad existeEspecialidad = especialidadRepository.findById(request.getEspecialidad().getId_especialidad()).orElse(null);
        if(null==existeEspecialidad){
            Especialidad especialidadGuardada = especialidadRepository.save(request.getEspecialidad());
            request.setEspecialidad(especialidadGuardada);
        }

        usuarioRepository.save(request.getUsuario());
        doctorRepository.save(request);

        response.setIdTransaccion(idTransaccion);
        response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
        response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        return response;
    }


}
