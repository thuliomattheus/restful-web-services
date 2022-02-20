package com.in28minutes.rest.webservices.restfulwebservices.controller;

import java.time.LocalDateTime;

import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionamentoController {

  /********************************************************************************************************************/
  /************************************************** URI VERSIONING **************************************************/
  /********************************************************************************************************************/

  @GetMapping("/endpoint-qualquer/v1")
  public String getVersionamentoPorUrlV1() {
    return "retorno da versão 1";
  }

  @GetMapping("/endpoint-qualquer/v2")
  public Pair<String, LocalDateTime> getVersionamentoPorUrlV2() {
    String retornoDaVersao2 = "retorno da versão 2";

    return Pair.of(retornoDaVersao2, LocalDateTime.now());
  }




  /**********************************************************************************************************************************/
  /************************************************** REQUEST PARAMETER VERSIONING **************************************************/
  /**********************************************************************************************************************************/

  // Acessível através da URL: localhost:8080/outro-endpoint-qualquer?versao=1
  @GetMapping(value = "/outro-endpoint-qualquer", params = "versao=1")
  public String getVersionamentoPorParametroV1() {
    return "retorno da outra versão 1";
  }

  // Acessível através da URL: localhost:8080/outro-endpoint-qualquer?versao=2
  @GetMapping(value = "/outro-endpoint-qualquer", params = "versao=2")
  public Pair<String, LocalDateTime> getVersionamentoPorParametroV2() {
    String retornoDaVersao2 = "retorno da outra versão 2";

    return Pair.of(retornoDaVersao2, LocalDateTime.now());
  }

}
