package br.com.nordestebank.conta.service;

import java.util.List;

import org.springframework.data.domain.Page;
import br.com.nordestebank.conta.model.Conta;
import br.com.nordestebank.conta.model.ContaDTO;
import org.springframework.data.domain.Pageable;

public interface ContaService {
    public Page<ContaDTO> findAll(Pageable pageable);
    public List<ContaDTO> findAll();
    public Conta findById(Long id);
    public Conta saveConta(ContaDTO contaDTO);
    public Conta editConta(ContaDTO contaDTO);
    public void deleteConta(Long id);
}
