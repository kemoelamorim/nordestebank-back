package br.com.nordestebank.endereco.model;

import br.com.nordestebank.conta.model.Conta;
import br.com.nordestebank.endereco.model.enums.TipoEndereco;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EnderecoDTO {
    private Long id;
    private String cep;
    private TipoEndereco tipoEndereco;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String numero;
    private String localidade;
    private String uf;

    private Conta conta;
    public EnderecoDTO(Endereco endereco){
        this.cep = endereco.getCep();
        this.tipoEndereco = endereco.getTipoEndereco();
        this.logradouro = endereco.getLogradouro();
        this.complemento = endereco.getComplemento();
        this.bairro = endereco.getBairro();
        this.localidade = endereco.getLocalidade();
        this.numero = endereco.getNumero();
        this.uf = endereco.getUf();
        this.conta = endereco.getConta();
    }

    public static List<EnderecoDTO> converter(List<Endereco> enderecoList) {
        return enderecoList.stream().map(EnderecoDTO::new).collect(Collectors.toList());
    }
}