package com.in28minutes.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.rest.webservices.restfulwebservices.dto.UsuarioDTO;
import com.in28minutes.rest.webservices.restfulwebservices.dto.mapper.UsuarioMapper;
import com.in28minutes.rest.webservices.restfulwebservices.entity.Post;
import com.in28minutes.rest.webservices.restfulwebservices.entity.Usuario;
import com.in28minutes.rest.webservices.restfulwebservices.service.PostService;
import com.in28minutes.rest.webservices.restfulwebservices.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private PostService postService;

  @GetMapping("")
  public ResponseEntity<List<EntityModel<UsuarioDTO>>> getAllUsuarios() {
    List<UsuarioDTO> usuarios = usuarioService.findAll();

    List<EntityModel<UsuarioDTO>> models = usuarios.stream().map(this::addLinks).collect(Collectors.toList());

    return new ResponseEntity<>(models, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<UsuarioDTO>> getUsuarioById(@PathVariable long id) {
    EntityModel<UsuarioDTO> model = addLinks(usuarioService.findUsuarioById(id));

    return new ResponseEntity<>(model, HttpStatus.OK);
  }

  @PostMapping("/{id}/posts")
  public ResponseEntity<Post> createPostLinkedToUsuario(@PathVariable long id, @RequestBody Post novoPost) {
    Usuario usuarioAssociado = UsuarioMapper.fromDTO(usuarioService.findUsuarioById(id));
    novoPost.setUsuario(usuarioAssociado);
    novoPost = postService.save(novoPost);

    return new ResponseEntity<>(novoPost, HttpStatus.CREATED);
  }

  @PostMapping("")
  public ResponseEntity<EntityModel<UsuarioDTO>> saveNewUsuario(@RequestBody @Valid UsuarioDTO novoUsuario) {
    EntityModel<UsuarioDTO> model = addLinks(usuarioService.save(novoUsuario));

    return new ResponseEntity<>(model, HttpStatus.CREATED);
  }

  private EntityModel<UsuarioDTO> addLinks(UsuarioDTO usuarioDto) {

    EntityModel<UsuarioDTO> model = EntityModel.of(usuarioDto);
    Locale locale = LocaleContextHolder.getLocale();

    // O header Accept-Language pode ser usado para informar o idioma das messages.properties
    // Exemplos: pt, pt-BR, en, en-US, es, fr, it...

    model.add(linkTo(methodOn(UsuarioController.class)
        .getUsuarioById(usuarioDto.getId()))
        .withSelfRel()
        .withType(HttpMethod.GET.toString()));

    model.add(linkTo(methodOn(UsuarioController.class)
        .getAllUsuarios())
        .withRel(messageSource.getMessage("list.all.users", null, locale))
        .withType(HttpMethod.GET.toString()));

    model.add(linkTo(methodOn(UsuarioController.class)
        .saveNewUsuario(usuarioDto))
        .withRel(messageSource.getMessage("create.new.user", null, locale))
        .withType(HttpMethod.POST.toString()));

    return model;
  }
}
