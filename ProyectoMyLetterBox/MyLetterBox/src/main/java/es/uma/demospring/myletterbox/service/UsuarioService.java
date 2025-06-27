package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dto.UsuarioDTO;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> getListaUsuarios(){
        List<EntityUsuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();

        for ( EntityUsuario entityUsuario : listaUsuarios ){
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setUserId(entityUsuario.getUserId());
            usuarioDTO.setEmail(entityUsuario.getEmail());
            usuarioDTO.setRol(entityUsuario.getRol());
            usuarioDTO.setPassword(entityUsuario.getPassword());
            usuarioDTO.setUsername(entityUsuario.getUsername());

            listaUsuarioDTO.add(usuarioDTO);
        }

        return listaUsuarioDTO;
    }
}
