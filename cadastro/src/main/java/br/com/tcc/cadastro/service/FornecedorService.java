package br.com.tcc.cadastro.service;

import br.com.tcc.cadastro.model.Fornecedor;
import br.com.tcc.cadastro.model.dto.FornecedorCriarDTO;
import br.com.tcc.cadastro.model.dto.FornecedorDTO;
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

    public Fornecedor fromDTO(FornecedorCriarDTO fornecedorCriarDTO){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCnpj(fornecedorCriarDTO.getCnpj());
        fornecedor.setNomeFantasia(fornecedorCriarDTO.getNomeFantasia());
        fornecedor.setRazaoSocial(fornecedorCriarDTO.getRazaoSocial());
        return fornecedor;
    }

    public Fornecedor fromDTO(FornecedorDTO fornecedorDTO){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(fornecedorDTO.getId());
        fornecedor.setRazaoSocial(fornecedorDTO.getRazaoSocial());
        fornecedor.setNomeFantasia(fornecedorDTO.getNomeFantasia());
        fornecedor.setCnpj(fornecedorDTO.getCnpj());

        return fornecedor;
    }

    public Fornecedor update(Fornecedor fornecedor, Fornecedor fornecedorUpdate) {
        if(fornecedorUpdate.getRazaoSocial() != null){
            fornecedor.setRazaoSocial(fornecedorUpdate.getRazaoSocial());
        }
        if(fornecedorUpdate.getCnpj() != null){
            fornecedor.setCnpj(fornecedorUpdate.getCnpj());
        }
        if(fornecedorUpdate.getNomeFantasia() != null){
            fornecedor.setNomeFantasia(fornecedorUpdate.getNomeFantasia());
        }
        fornecedorRepository.save(fornecedor);

        return fornecedor;
    }
}
