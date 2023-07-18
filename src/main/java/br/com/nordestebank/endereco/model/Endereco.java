package br.com.nordestebank.endereco.model;

import javax.persistence.*;

import br.com.nordestebank.conta.model.Conta;
import br.com.nordestebank.endereco.model.enums.TipoEndereco;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_ENDERECO")
    private TipoEndereco tipoEndereco;

    @Column(name = "CEP")
    private  String cep;

    @Column(name = "BAIRRO")
    private String bairro;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CIDADE")
    private String localidade;

    @Column(name = "UF")
    private String uf;

    @ManyToOne
    @JoinColumn(name = "CONTA_ID", nullable = true, foreignKey = @ForeignKey(name = "fkey_conta"))
    private Conta conta;

    public Endereco(EnderecoDTO enderecoDTO){
        this.id = enderecoDTO.getId();
        this.cep = enderecoDTO.getCep();
        this.logradouro = enderecoDTO.getLogradouro();
        this.tipoEndereco = enderecoDTO.getTipoEndereco();
        this.bairro = enderecoDTO.getBairro();
        this.complemento = enderecoDTO.getComplemento();
        this.localidade = enderecoDTO.getLocalidade();
        this.numero = enderecoDTO.getNumero();
        this.uf = enderecoDTO.getUf();
        this.conta = enderecoDTO.getConta();
    }
}
