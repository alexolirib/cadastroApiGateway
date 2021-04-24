package br.com.tcc.cadastro.service;

import br.com.tcc.cadastro.model.Fornecedor;
import br.com.tcc.cadastro.model.dto.FornecedorCriarDTO;
import br.com.tcc.cadastro.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Fornecedor criar(Fornecedor fornecedor){
        fornecedor.setId(null);
        return fornecedorRepository.save(fornecedor);

    }

    public List<Fornecedor> obterTodos(){
        return fornecedorRepository.findAll();
    }

    public Fornecedor obterPorId(Integer id){
        Optional<Fornecedor> optionalCategoria =  fornecedorRepository.findById(id);
        if (optionalCategoria.isPresent()){
            return optionalCategoria.get();
        }
        return null;
    }

    public void delete(Integer id){
        fornecedorRepository.deleteById(id);
    }

    public Fornecedor fromDTO(FornecedorCriarDTO categoriaCriarDTO){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj(categoriaCriarDTO.getCnpj());
        fornecedor.setNomeFantasia(categoriaCriarDTO.getNomeFantasia());
        return fornecedor;
    }
}
