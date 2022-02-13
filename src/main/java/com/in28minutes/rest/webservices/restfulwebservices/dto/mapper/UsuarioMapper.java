package com.in28minutes.rest.webservices.restfulwebservices.dto.mapper;

import com.in28minutes.rest.webservices.restfulwebservices.dto.UsuarioDTO;
import com.in28minutes.rest.webservices.restfulwebservices.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    private UsuarioMapper() {}

    public static Usuario fromDTO(UsuarioDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setLogin(dto.getLogin());
        usuario.setPassword(dto.getPassword());
        usuario.setEmail(dto.getEmail());

        return usuario;
    }

    public static UsuarioDTO fromEntity(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();

        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setLogin(usuario.getLogin());
        dto.setPassword(usuario.getPassword());
        dto.setEmail(usuario.getEmail());

        return dto;
    }

    public static List<UsuarioDTO> getListFromEntity(List<Usuario> usuarios) {
        return usuarios.stream()
              .map(UsuarioMapper::fromEntity)
              .collect(Collectors.toList());
    }
}
