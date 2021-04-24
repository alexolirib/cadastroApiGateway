package br.com.tcc.cadastro.repository;

import br.com.tcc.cadastro.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
}
