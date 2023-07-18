package br.com.nordestebank.endereco.model.enums;

public enum TipoEndereco {
  RESIDENCIAL("RESIDENCIAL"),
  COMERCIAL("COMERCIL");
  private String descricao;

  TipoEndereco(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }
}
