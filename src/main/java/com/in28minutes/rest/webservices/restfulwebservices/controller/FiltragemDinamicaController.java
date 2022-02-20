package com.in28minutes.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.in28minutes.rest.webservices.restfulwebservices.dto.UsuarioDTO;
import com.in28minutes.rest.webservices.restfulwebservices.service.UsuarioService;

@RestController
@RequestMapping("/filtro-dinamico")
public class FiltragemDinamicaController {

  @Autowired
  private UsuarioService usuarioService;

  @GetMapping("")
  public ResponseEntity<MappingJacksonValue> getAllUsuariosWithoutLoginAndPassword() {
    List<UsuarioDTO> usuarios = usuarioService.findAll();

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
        .filterOutAllExcept("id", "nome", "email");

    FilterProvider filters = new SimpleFilterProvider()
        .addFilter("UsuarioDTOFilter", filter);

    MappingJacksonValue mapping = new MappingJacksonValue(usuarios);

    mapping.setFilters(filters);

    return new ResponseEntity<>(mapping, HttpStatus.OK);
  }

}
