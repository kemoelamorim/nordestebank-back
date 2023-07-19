package br.com.nordestebank.conta.service;

import br.com.nordestebank.conta.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.nordestebank.conta.model.Conta;
import br.com.nordestebank.conta.model.ContaDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService{
  @Autowired
  private ContaRepository contaRepository;
  @Override
  public void deleteConta(Long id) {
    if(id != null){
      Optional<Conta> contaOptional = this.contaRepository.findById(id);
      if(contaOptional.isPresent()){
        this.contaRepository.deleteEnderecosByContaId(id);
        this.contaRepository.delete(contaOptional.get());
      }else{
        throw new RuntimeException("Conta não encontrada.");
      }
    }
  }

  @Override
  public Conta editConta(ContaDTO contaDTO) {
    if(contaDTO != null){
      Optional<Conta> clienteOptional = this.contaRepository.findById(contaDTO.getId());
      if(clienteOptional.isPresent()){
        Conta conta = new Conta(contaDTO);
        return this.contaRepository.save(conta);
      }else{
        throw new RuntimeException("Conta não encontrada.");
      }
    } else{
      throw new RuntimeException("Problema ao editar conta.");
    }
  }

  @Override
  public Page<ContaDTO> findAll(Pageable pageable) {
    Page<Conta> contas = this.contaRepository.findAll(pageable);
	  long pageCount = contas.getTotalElements();
	//transforma a lista contas.getContent(); na lista do DTO
    List<ContaDTO> listDTO = ContaDTO.converter(contas.getContent());
    Page<ContaDTO> listaPageDTO = new PageImpl<>(listDTO, pageable, pageCount);
    return listaPageDTO;
  }

  @Override
  public List<ContaDTO> findAll() {
    List<Conta> contas = this.contaRepository.findAll();
    List<ContaDTO> listDTO = ContaDTO.converter(contas);
    return listDTO;
  }

  @Override
  public Conta findById(Long id) {
    if(id != null){
      Optional<Conta> contaOptional = this.contaRepository.findById(id);
      if(contaOptional.isPresent()){
        return contaOptional.get();
      }else{
        throw new RuntimeException("Conta não encontrada.");
      }
    } else {
      throw new RuntimeException("Problema ao buscar Conta.");
    }
  }

  @Override
  public Conta saveConta(ContaDTO contaDTO) {
    if(contaDTO != null){
      return this.contaRepository.save(new Conta(contaDTO));
    }
    throw new RuntimeException("Problema ao salvar conta.");
  }

  @Override
  public List<ContaDTO> findByNome(String search) {
  
    return ContaDTO.converter(contaRepository.findByTitularLike(search));
  }
}
