package com.in28minutes.rest.webservices.restfulwebservices.exception;

public class UsuarioNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
