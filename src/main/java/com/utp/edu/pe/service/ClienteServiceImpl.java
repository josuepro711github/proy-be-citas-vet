package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Especialidad;
import com.utp.edu.pe.model.Usuario;
import com.utp.edu.pe.repository.ClienteRepository;

import com.utp.edu.pe.repository.UsuarioRepository;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private PropertiesInterno propertiesInterno;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public BodyResponse registrarCliente(Cliente request) {

        BodyResponse response = new BodyResponse();
        Usuario existeUsuario = usuarioRepository.findByEmail(request.getUsuario().getEmail());
        if(null!=existeUsuario){
            response.setCodigoRespuesta(propertiesInterno.idf2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf2Mensaje.replace(Constantes.TAG_USUARIO, existeUsuario.getEmail()));
            return response;
        }

        request.getUsuario().setContrasenia(new BCryptPasswordEncoder().encode(request.getUsuario().getContrasenia()));
        request.getUsuario().getRol().setId_rol(Constantes.ROL_CLIENTE);

        Usuario usuarioGuardado = usuarioRepository.save(request.getUsuario());
        request.setUsuario(usuarioGuardado);

        try {
            clienteRepository.save(request);
            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        } catch (DataIntegrityViolationException e){
            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));
        }

        return response;
    }


}
