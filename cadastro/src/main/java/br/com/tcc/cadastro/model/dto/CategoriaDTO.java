package br.com.tcc.cadastro.model.dto;

import br.com.tcc.cadastro.model.Categoria;

public class CategoriaDTO {
    private Integer id;
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
