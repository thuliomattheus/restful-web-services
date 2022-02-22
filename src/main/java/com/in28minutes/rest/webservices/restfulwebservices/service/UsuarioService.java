package com.in28minutes.rest.webservices.restfulwebservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.rest.webservices.restfulwebservices.dto.UsuarioDTO;
import com.in28minutes.rest.webservices.restfulwebservices.dto.mapper.UsuarioMapper;
import com.in28minutes.rest.webservices.restfulwebservices.entity.Usuario;
import com.in28minutes.rest.webservices.restfulwebservices.exception.UsuarioNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> findAll() {
      return UsuarioMapper.getListFromEntity(usuarioRepository.findAll());
    }

    public UsuarioDTO findUsuarioById(long id) {
        return UsuarioMapper.fromEntity(usuarioRepository.findUsuarioById(id).orElseThrow(UsuarioNotFoundException::new));
    }

    public UsuarioDTO save(UsuarioDTO usuarioDto) {
        Usuario usuario = usuarioRepository.save(UsuarioMapper.fromDTO(usuarioDto));

        return UsuarioMapper.fromEntity(usuario);
    }
}
