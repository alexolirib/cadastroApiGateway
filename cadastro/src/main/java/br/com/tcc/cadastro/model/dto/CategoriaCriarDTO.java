package br.com.tcc.cadastro.model.dto;

import br.com.tcc.cadastro.model.Categoria;

public class CategoriaCriarDTO {
    private String nome;

    public CategoriaCriarDTO() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
