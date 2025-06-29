package es.uma.demospring.myletterbox.service;

import es.uma.demospring.myletterbox.dao.UsuarioRepository;
import es.uma.demospring.myletterbox.dto.UsuarioDTO;
import es.uma.demospring.myletterbox.entity.EntityUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/*
 * Autor(es): Rafael Sáez Arana (100%)
 */
@Service
public class UsuarioService {
    @Autowired
    protected UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> getListaUsuarios(){
        List<EntityUsuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();

        for ( EntityUsuario entityUsuario : listaUsuarios ){
            UsuarioDTO usuarioDTO = toDTO(entityUsuario);
            listaUsuarioDTO.add(usuarioDTO);
        }

        return listaUsuarioDTO;
    }

    public UsuarioDTO getUsuarioById(int id){
        EntityUsuario usuario = usuarioRepository.findById(id).orElse(null);
        UsuarioDTO usuarioDTO = toDTO(usuario);
        return usuarioDTO;
    }

    public void update(UsuarioDTO usuarioDTO){
        EntityUsuario usuario = usuarioRepository.findById(usuarioDTO.getUserId()).orElse(null);

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setUsername(usuarioDTO.getUsername());

        usuarioRepository.save(usuario);
    }

    public boolean create(UsuarioDTO usuarioDTO){
        boolean exist = false;

        if(usuarioRepository.existUsuario(usuarioDTO.getUsername()) != null){
            return true;
        }

        EntityUsuario usuario = new EntityUsuario();

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setRol(usuarioDTO.getRol());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setUsername(usuarioDTO.getUsername());

        usuarioRepository.save(usuario);
        return exist;
    }

    public void delete(int id){
        EntityUsuario usuario = usuarioRepository.findById(id).orElse(null);
        usuarioRepository.delete(usuario);
    }
    private static UsuarioDTO toDTO(EntityUsuario entityUsuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUserId(entityUsuario.getUserId());
        usuarioDTO.setEmail(entityUsuario.getEmail());
        usuarioDTO.setRol(entityUsuario.getRol());
        usuarioDTO.setPassword(entityUsuario.getPassword());
        usuarioDTO.setUsername(entityUsuario.getUsername());
        return usuarioDTO;
    }
    public static List<String> getRolesUsuario(){
        List<String> roles = new ArrayList<>();
        roles.add("administrador");
        roles.add("usuario");
        roles.add("recomendador");
        roles.add("analista");
        roles.add("editor");
        return roles;
    }
}
