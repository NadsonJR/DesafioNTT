package org.desafio.controller;

import lombok.extern.log4j.Log4j2;
import org.desafio.model.CepResponse;
import org.desafio.service.CepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/cep")
public class CepController {
    private final CepService cepService;
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<Object> getCep(@PathVariable String cep) {
        log.info("Recebendo solicitação para buscar CEP:");
        try {
            // Chama o serviço para buscar informações do CEP
            Object response = cepService.buscarCep(cep);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Retorna um erro genérico para o cliente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a solicitação: " + e.getMessage());
        }
    }


}
