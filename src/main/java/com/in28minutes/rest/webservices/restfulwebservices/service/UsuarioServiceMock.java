package com.in28minutes.rest.webservices.restfulwebservices.service;

import com.in28minutes.rest.webservices.restfulwebservices.exception.UsuarioNotFoundException;
import com.in28minutes.rest.webservices.restfulwebservices.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceMock {

    public List<Usuario> findAll() {
        Usuario usuario;
        List<Usuario> usuarios = new ArrayList<>();

        for(long i=1; i<=5; i++){
            usuario = new Usuario();
            usuario.setId(i);
            usuario.setNome("Usuário " + i);
            usuario.setEmail("usuario" + i + "@hotmail.com");
            usuario.setLogin(usuario.getNome());
            usuario.setPassword(usuario.getNome());
            usuarios.add(usuario);
        }

        return usuarios;
    }

    public Optional<Usuario> findUsuarioById(long id) {
        List<Usuario> usuarios = findAll();

        if(id>0 && id<=usuarios.size()){
            return Optional.of(usuarios.get((int) id-1));
        }
        else {
            throw new UsuarioNotFoundException();
        }
    }
}
