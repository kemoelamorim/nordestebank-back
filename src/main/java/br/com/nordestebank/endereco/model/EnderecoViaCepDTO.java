package br.com.nordestebank.endereco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoViaCepDTO {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    public EnderecoViaCepDTO(EnderecoViaCepDTO enderecoViaCepDTO){
        this.cep = enderecoViaCepDTO.getCep();
        this.logradouro = enderecoViaCepDTO.getLogradouro();
        this.bairro = enderecoViaCepDTO.getBairro();
        this.localidade = enderecoViaCepDTO.getLocalidade();
        this.uf = enderecoViaCepDTO.getUf();
    }
}
