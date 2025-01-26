package org.desafio.controller;

import org.desafio.model.PessoaModel;
import org.desafio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaModel> createPessoa(@RequestBody PessoaModel pessoa) {
        PessoaModel createdPessoa = pessoaService.createPessoa(pessoa);
        return ResponseEntity.ok(createdPessoa);
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> getAllPessoas() {
        List<PessoaModel> pessoas = pessoaService.getAllPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> getPessoaById(@PathVariable Long id) {
        PessoaModel pessoa = pessoaService.getPessoaById(id).orElseThrow(() -> new RuntimeException("Pessoa not found"));
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> updatePessoa(@PathVariable Long id, @RequestBody PessoaModel pessoaDetails) {
        PessoaModel updatedPessoa = pessoaService.updatePessoa(id, pessoaDetails);
        return ResponseEntity.ok(updatedPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }
}
