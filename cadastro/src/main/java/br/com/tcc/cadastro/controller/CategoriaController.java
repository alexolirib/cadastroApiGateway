package br.com.tcc.cadastro.controller;

import br.com.tcc.cadastro.model.Categoria;
import br.com.tcc.cadastro.model.Produto;
import br.com.tcc.cadastro.model.dto.CategoriaCriarDTO;
import br.com.tcc.cadastro.model.dto.CategoriaDTO;
import br.com.tcc.cadastro.model.dto.ProdutoDTO;
import br.com.tcc.cadastro.service.CategoriaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categoria")
@Api(tags={"categoria"})
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar(@RequestBody CategoriaCriarDTO categoriaCriarDTO){
        Categoria categoria = categoriaService.fromDTO(categoriaCriarDTO);
        categoria = categoriaService.criar(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoriaDTO(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obterTodos(){
        List<Categoria> categorias = categoriaService.obterTodos();
        return ResponseEntity.ok().body(categorias.stream().map(CategoriaDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obterPorId(@PathVariable("id") Integer id){
        Categoria categoria = categoriaService.obterPorId(id);
        if (categoria == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new CategoriaDTO(categoria));
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable("id") Integer id, @RequestBody final CategoriaDTO categoriaDTO){
        Categoria categoria = categoriaService.obterPorId(id);
        if (categoria == null){
            return ResponseEntity.notFound().build();
        }
        Categoria categoriaUpdate = categoriaService.fromDTO(categoriaDTO);
        categoria = categoriaService.update(categoria, categoriaUpdate);

        return ResponseEntity.ok().body(new CategoriaDTO(categoria));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Categoria categoria = categoriaService.obterPorId(id);
        if (categoria == null){
            return ResponseEntity.notFound().build();
        }
        categoriaService.delete(categoria.getId());
        return ResponseEntity.noContent().build();
    }
}
