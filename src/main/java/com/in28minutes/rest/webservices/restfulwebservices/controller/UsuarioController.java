package com.in28minutes.rest.webservices.restfulwebservices.controller;

import com.in28minutes.rest.webservices.restfulwebservices.dto.UsuarioDTO;
import com.in28minutes.rest.webservices.restfulwebservices.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable long id) {
        UsuarioDTO usuarioDto = addLinks(usuarioService.findUsuarioById(id));

        return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<UsuarioDTO> saveNewUsuario(@RequestBody @Valid UsuarioDTO novoUsuario) {
        UsuarioDTO usuarioDto = addLinks(usuarioService.save(novoUsuario));

        return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
    }

    private UsuarioDTO addLinks(UsuarioDTO usuarioDto) {

        usuarioDto.add(
            linkTo(
                methodOn(UsuarioController.class)
                    .getUsuarioById(usuarioDto.getId())
            )
            .withSelfRel()
            .withType(HttpMethod.GET.toString())
        );

        usuarioDto.add(
            linkTo(
                methodOn(UsuarioController.class)
                    .getAllUsuarios()
            )
            .withRel("listar todos os usuários")
            .withType(HttpMethod.GET.toString())
        );

        usuarioDto.add(
            linkTo(
                methodOn(UsuarioController.class)
                    .saveNewUsuario(usuarioDto)
            )
            .withRel("cadastrar novo usuário")
            .withType(HttpMethod.POST.toString())
        );

        return usuarioDto;
    }
}
