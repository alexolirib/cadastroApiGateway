package br.com.tcc.cadastro.service;

import br.com.tcc.cadastro.model.Categoria;
import br.com.tcc.cadastro.model.Fornecedor;
import br.com.tcc.cadastro.model.Produto;
import br.com.tcc.cadastro.model.dto.ProdutoCriarDTO;
import br.com.tcc.cadastro.model.dto.ProdutoDTO;
import br.com.tcc.cadastro.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FornecedorService fornecedorService;

    public Produto criar(Produto produto){
        produto.setId(null);
        return produtoRepository.save(produto);

    }

    public List<Produto> obterTodos(){
        return produtoRepository.findAll();
    }

    public Produto obterPorId(Integer id){
        Optional<Produto> optionalCategoria =  produtoRepository.findById(id);
        if (optionalCategoria.isPresent()){
            return optionalCategoria.get();
        }
        return null;
    }

    public void delete(Integer id){
        produtoRepository.deleteById(id);
    }

    public Produto fromDTO(ProdutoCriarDTO produtoCriarDTO){
        Produto produto = new Produto();
        produto.setNome(produtoCriarDTO.getNome());
        produto.setDescricao(produtoCriarDTO.getDescricao());
        produto.setValor(produtoCriarDTO.getValor());
        Categoria categoria = categoriaService.obterPorId(produtoCriarDTO.getCategoriaId());
        if (categoria != null){
            produto.setCategoria(categoria);
        }
        Fornecedor fornecedor = fornecedorService.obterPorId(produtoCriarDTO.getFornecedorId());
        if (fornecedor != null){
            produto.setFornecedor(fornecedor);
        }
        return produto;
    }

    public Produto fromDTO(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setNome(produto.getNome());
        produto.setValor(produtoDTO.getValor());
        Categoria categoria = categoriaService.obterPorId(produtoDTO.getCategoriaId());
        if (categoria != null){
            produto.setCategoria(categoria);
        }
        Fornecedor fornecedor = fornecedorService.obterPorId(produtoDTO.getFornecedorId());
        if (fornecedor != null){
            produto.setFornecedor(fornecedor);
        }
        return produto;
    }

    public Produto update(Produto produto, Produto produtoUpdate){
        if (produtoUpdate.getDescricao() != null){
            produto.setDescricao(produtoUpdate.getDescricao());
        }
        if (produtoUpdate.getNome() != null){
            produto.setNome(produto.getNome());
        }
        if (produtoUpdate.getValor() != null){
            produto.setValor(produtoUpdate.getValor());
        }
        if(produtoUpdate.getCategoria() != null){
            produto.setCategoria(produtoUpdate.getCategoria());
        }
        if (produtoUpdate.getFornecedor() != null){
            produto.setFornecedor(produtoUpdate.getFornecedor());
        }
        return produto;
    }

}
