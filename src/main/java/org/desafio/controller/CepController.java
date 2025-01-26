package org.desafio.controller;

import org.desafio.model.CepResponse;
import org.desafio.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cep")
public class CepController {
    private final CepService cepService;
    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public ResponseEntity<CepResponse> buscarCep(@PathVariable String cep) {
        try {
            CepResponse response = cepService.buscarCep(cep);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
