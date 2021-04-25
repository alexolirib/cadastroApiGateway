package br.com.tcc.cadastro.controller;
import br.com.tcc.cadastro.model.Fornecedor;
import br.com.tcc.cadastro.model.dto.FornecedorCriarDTO;
import br.com.tcc.cadastro.model.dto.FornecedorDTO;
import br.com.tcc.cadastro.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<FornecedorDTO> criar(@RequestBody FornecedorCriarDTO fornecedorCriarDTO){
        Fornecedor categoria = fornecedorService.fromDTO(fornecedorCriarDTO);
        categoria = fornecedorService.criar(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new FornecedorDTO(categoria));
    }

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> obterTodos(){
        List<Fornecedor> categorias = fornecedorService.obterTodos();
        return ResponseEntity.ok().body(categorias.stream().map(FornecedorDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> obterPorId(@PathVariable("id") Integer id){
        Fornecedor fornecedor = fornecedorService.obterPorId(id);
        if (fornecedor == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new FornecedorDTO(fornecedor));
    }

    @PutMapping("{id}")
    public ResponseEntity<FornecedorDTO> update(@PathVariable("id") Integer id, @RequestBody final FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = fornecedorService.obterPorId(id);
        if (fornecedor == null){
            return ResponseEntity.notFound().build();
        }
        Fornecedor fornecedorUpdate = fornecedorService.fromDTO(fornecedorDTO);
        fornecedor = fornecedorService.update(fornecedor, fornecedorUpdate);

        return ResponseEntity.ok().body(new FornecedorDTO(fornecedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Fornecedor fornecedor = fornecedorService.obterPorId(id);
        if (fornecedor == null){
            return ResponseEntity.notFound().build();
        }
        fornecedorService.delete(fornecedor.getId());
        return ResponseEntity.noContent().build();
    }
}
