package br.com.tcc.cadastro.repository;

import br.com.tcc.cadastro.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Integer> {
}
