package br.com.nordestebank.conta.model;

import java.util.List;
import java.util.stream.Collectors;
import br.com.nordestebank.conta.model.enums.TipoConta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContaDTO {
  private Long id;
  private TipoConta tipoConta;
  private String titular;
  private String cpfCnpj;
  private String dddTelefone;
  private String email;
  public ContaDTO(Conta conta) {
      if(conta != null) {
        this.id = conta.getId();
        this.tipoConta = conta.getTipoConta();
        this.titular = conta.getTitular();
        this.cpfCnpj = conta.getCpfCnpj();
        this.dddTelefone = conta.getDddTelefone();
        this.email = conta.getEmail();
      }
  }
  public static List<ContaDTO> converter(List<Conta> contas) {
    return contas.stream().map(ContaDTO::new).collect(Collectors.toList());
  }
}
