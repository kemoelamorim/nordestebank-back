package br.com.nordestebank.endereco.service;

import br.com.nordestebank.endereco.model.EnderecoViaCepDTO;

public interface ServiceViaCep {

    public EnderecoViaCepDTO getEnderecoViaCep(String cep);
}