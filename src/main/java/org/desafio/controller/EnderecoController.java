package org.desafio.controller;

import lombok.extern.log4j.Log4j2;
import org.desafio.service.EnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }
    @GetMapping("/cep/{cep}")
    public ResponseEntity<Object> getCep(@PathVariable String cep) {
        log.info("Recebendo solicitação para buscar CEP:"+ cep);
        try {
            // Chama o serviço para buscar informações do CEP
            Object response = enderecoService.fetchCep(cep);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Retorna um erro genérico para o cliente
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar a solicitação: " + e.getMessage());
        }
    }
}
