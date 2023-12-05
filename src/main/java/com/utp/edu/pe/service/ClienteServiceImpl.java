package com.utp.edu.pe.service;

import com.utp.edu.pe.bean.BodyResponse;
import com.utp.edu.pe.model.Cliente;
import com.utp.edu.pe.model.Usuario;
import com.utp.edu.pe.repository.ClienteRepository;

import com.utp.edu.pe.repository.UsuarioRepository;
import com.utp.edu.pe.util.Constantes;
import com.utp.edu.pe.util.PropertiesInterno;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private PropertiesInterno propertiesInterno;
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ImagenService imagenService;


    @Override
    public BodyResponse registrarCliente(Cliente request, MultipartFile imagen) {

        BodyResponse response = new BodyResponse();

        try {
            Usuario existeUsuario = usuarioRepository.findByEmail(request.getUsuario().getEmail());
            if(null!=existeUsuario){
                response.setCodigoRespuesta(propertiesInterno.idf2Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf2Mensaje.replace(Constantes.TAG_USUARIO, existeUsuario.getEmail()));
                return response;
            }

            request.getUsuario().setContrasenia(new BCryptPasswordEncoder().encode(request.getUsuario().getContrasenia()));
            request.getUsuario().getRol().setId_rol(Constantes.ROL_CLIENTE);

            String nombreImagen = imagenService.cargarImagen(imagen,"clientes");
            request.getUsuario().setImagen(nombreImagen);

            Usuario usuarioGuardado = usuarioRepository.save(request.getUsuario());
            request.setUsuario(usuarioGuardado);

            clienteRepository.save(request);
            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        } catch (DataIntegrityViolationException e){
            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));

        }

        return response;
    }


    @Override
    public BodyResponse actualizarCliente(Cliente request, MultipartFile imagen) {

        BodyResponse response = new BodyResponse();

        try {
            Usuario existeUsuario = usuarioRepository.findByEmail(request.getUsuario().getEmail());

            if(null!=existeUsuario) {
                request.getUsuario().setContrasenia(new BCryptPasswordEncoder().encode(request.getUsuario().getContrasenia()));
                request.getUsuario().getRol().setId_rol(Constantes.ROL_CLIENTE);

                imagenService.eliminarImagen(existeUsuario.getImagen());
                String nombreImagen = imagenService.cargarImagen(imagen, "clientes");
                request.getUsuario().setImagen(nombreImagen);


                Usuario usuarioGuardado = usuarioRepository.save(request.getUsuario());
                request.setUsuario(usuarioGuardado);
            } else{
                response.setCodigoRespuesta(propertiesInterno.idf1Codigo);
                response.setMensajeRespuesta(propertiesInterno.idf1Mensaje.replace(Constantes.TAG_USUARIO, existeUsuario.getEmail()));
                return response;
            }

        } catch (DataIntegrityViolationException e){
            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));

        }

        return response;
    }

    @Override
    public BodyResponse actualizarCliente(Cliente request, MultipartFile imagen) {
        BodyResponse response = new BodyResponse();
        try {
            Cliente clienteEncontrado =clienteRepository.findById(request.getId_cliente()).orElse(null);
            request.getUsuario().setContrasenia(clienteEncontrado.getUsuario().getContrasenia());
            if(request.getUsuario().getImagen().equals("cambiado")){
                imagenService.eliminarImagen(clienteEncontrado.getUsuario().getImagen(),"clientes");
                String nombreImagen = imagenService.cargarImagen(imagen,"clientes");
                request.getUsuario().setImagen(nombreImagen);
            }
            System.out.println("Request "+ request.getUsuario());
            Usuario usuarioGuardado = usuarioRepository.saveAndFlush(request.getUsuario());
            request.setUsuario(usuarioGuardado);

            clienteRepository.saveAndFlush(request);
            response.setCodigoRespuesta(propertiesInterno.idf0Codigo);
            response.setMensajeRespuesta(propertiesInterno.idf0Mensaje);

        }catch (DataIntegrityViolationException e){
            response.setCodigoRespuesta(propertiesInterno.idt2Codigo);
            response.setMensajeRespuesta(propertiesInterno.idt2Mensaje.replace(Constantes.TAG_MENSAJE, e.getRootCause().getMessage()));
        }
        return response;
    }





}
