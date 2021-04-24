package br.com.tcc.cadastro.controller;

import br.com.tcc.cadastro.model.Produto;
import br.com.tcc.cadastro.model.dto.ProdutoCriarDTO;
import br.com.tcc.cadastro.model.dto.ProdutoDTO;
import br.com.tcc.cadastro.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoCriarDTO produtoCriarDTO){
        Produto produto = produtoService.fromDTO(produtoCriarDTO);
        produto = produtoService.criar(produto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> obterTodos(){
        List<Produto> produtos = produtoService.obterTodos();
        return ResponseEntity.ok().body(produtos.stream().map(ProdutoDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> obterPorId(@PathVariable("id") Integer id){
        Produto produto = produtoService.obterPorId(id);
        if (produto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new ProdutoDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Produto produto = produtoService.obterPorId(id);
        if (produto == null){
            return ResponseEntity.notFound().build();
        }
        produtoService.delete(produto.getId());
        return ResponseEntity.noContent().build();
    }
}
