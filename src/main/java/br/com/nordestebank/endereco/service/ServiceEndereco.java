package br.com.nordestebank.endereco.service;

import br.com.nordestebank.endereco.model.Endereco;
import br.com.nordestebank.endereco.model.EnderecoDTO;

import java.util.List;

public interface ServiceEndereco {

  public List<EnderecoDTO> enderecosContaById(Long id);
  public Endereco saveEndereco(EnderecoDTO enderecoDTO);
  public Endereco editEndereco(EnderecoDTO enderecoDTO);
  public void deleteEndereco(Long id);
}
