package br.com.nordestebank.conta.model.enums;

public enum TipoConta {
    FISICA("FISICA"),
    JURIDICA("JURIDICA");
    private String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}