package org.desafio.service;

import org.desafio.model.PessoaModel;
import org.desafio.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaModel createPessoa(PessoaModel pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public List<PessoaModel> getAllPessoas() {
        return pessoaRepository.findAll();
    }

    public Optional<PessoaModel> getPessoaById(Long id) {
        return pessoaRepository.findById(id);
    }

    public PessoaModel updatePessoa(Long id, PessoaModel pessoaDetails) {
        PessoaModel pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa not found"));
        pessoa.setNome(pessoaDetails.getNome());
        pessoa.setCpf(pessoaDetails.getCpf());
        pessoa.setDataNascimento(pessoaDetails.getDataNascimento());
        pessoa.setEmail(pessoaDetails.getEmail());
        pessoa.setTelefone(pessoaDetails.getTelefone());
        pessoa.setEndereco(pessoaDetails.getEndereco());
        return pessoaRepository.save(pessoa);
    }

    public void deletePessoa(Long id) {
        PessoaModel pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa not found"));
        pessoaRepository.delete(pessoa);
    }
}
