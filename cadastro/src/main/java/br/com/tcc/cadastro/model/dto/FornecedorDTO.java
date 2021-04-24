package br.com.tcc.cadastro.model.dto;

import br.com.tcc.cadastro.model.Fornecedor;

public class FornecedorDTO {

    private Integer id;

    private String nomeFantasia;

    private String cnpj;

    private String razaoSocial;

    public FornecedorDTO() {
    }

    public FornecedorDTO(Fornecedor fornecedor) {
        this.id = fornecedor.getId();
        this.nomeFantasia = fornecedor.getNomeFantasia();
        this.cnpj = fornecedor.getCnpj();
        this.razaoSocial = fornecedor.getRazaoSocial();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}
