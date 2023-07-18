package br.com.nordestebank.conta.model;

import javax.persistence.*;
import br.com.nordestebank.conta.model.enums.TipoConta;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TB_CONTA")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTA")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_CONTA")
    private TipoConta tipoConta;

    @Column(name = "TITULAR")
    private String titular;

    @Column(name = "CPF_CNPJ")
    private String cpfCnpj;

    @Column(name = "DDD_TELEFONE")
    private String dddTelefone;

    @Column(name = "EMAIL")
    private String email;


    public Conta(ContaDTO contaDTO){
        this.id = contaDTO.getId();
        this.tipoConta = contaDTO.getTipoConta();
        this.titular = contaDTO.getTitular();
        this.cpfCnpj = contaDTO.getCpfCnpj();
        this.dddTelefone = contaDTO.getDddTelefone();
        this.email = contaDTO.getEmail();
    }
}
