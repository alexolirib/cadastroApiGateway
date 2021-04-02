package br.com.tcc.cadastro.repository;

import br.com.tcc.cadastro.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
