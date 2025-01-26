package org.desafio.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class PessoaModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @NonNull
    private String nome;
    @NonNull
    private String cpf;
    @NonNull
    private String dataNascimento;
    @NonNull
    private String email;
    @NonNull
    private String telefone;
    private EnderecoModel endereco;
    public PessoaModel() {
    }
    public PessoaModel(String nome, String cpf, String dataNascimento, String email, String telefone, EnderecoModel endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

}
