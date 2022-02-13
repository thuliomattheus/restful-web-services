package com.in28minutes.rest.webservices.restfulwebservices.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.passay.PasswordData;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in28minutes.rest.webservices.restfulwebservices.config.annotation.ValidPassword;

public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    private Long id;

    @NotBlank(message = "precisa possuir ao menos um caractere diferente de espaço em branco")
    private String nome;

    @NotBlank(message = "precisa possuir ao menos um caractere diferente de espaço em branco")
    private String login;

    @NotBlank(message = "precisa possuir ao menos um caractere diferente de espaço em branco")
    private String password;

    @Email(message = "não está no formato esperado: *@*")
    @NotNull(message = "não pode ser nulo")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    @ValidPassword
    public PasswordData getData() {
      PasswordData data = new PasswordData(password);
      data.setUsername(login);

      return data;
    }
}
