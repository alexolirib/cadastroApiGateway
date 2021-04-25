package br.com.tcc.cadastro.service;

import br.com.tcc.cadastro.model.Categoria;
import br.com.tcc.cadastro.model.Fornecedor;
import br.com.tcc.cadastro.model.dto.CategoriaCriarDTO;
import br.com.tcc.cadastro.model.dto.CategoriaDTO;
import br.com.tcc.cadastro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria criar(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);

    }

    public List<Categoria> obterTodos(){
        return categoriaRepository.findAll();
    }

    public Categoria obterPorId(Integer id){
        Optional<Categoria> optionalCategoria =  categoriaRepository.findById(id);
        if (optionalCategoria.isPresent()){
            return optionalCategoria.get();
        }
        return null;
    }

    public void delete(Integer id){
        categoriaRepository.deleteById(id);
    }

    public Categoria fromDTO(CategoriaCriarDTO categoriaCriarDTO){
        Categoria categoria = new Categoria();
        categoria.setNome(categoriaCriarDTO.getNome());
        return categoria;
    }

    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());

        return categoria;
    }

    public Categoria update(Categoria categoria, Categoria categoriaUpdate) {
        if(categoriaUpdate.getNome() != null){
            categoria.setNome(categoriaUpdate.getNome());
        }
        categoriaRepository.save(categoria);

        return categoria;
    }

}
