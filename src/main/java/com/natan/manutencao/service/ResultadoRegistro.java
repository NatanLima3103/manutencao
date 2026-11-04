package com.natan.manutencao.service;

public enum ResultadoRegistro {

    OK("Manutenção registrada com sucesso."),
    KM_INVALIDO("A quilometragem não pode ser negativa."),
    CUSTO_INVALIDO("O custo não pode ser negativo."),
    DATA_FUTURA("A data do serviço não pode estar no futuro.");

    private final String mensagem;

    ResultadoRegistro(String mensagem){
        this.mensagem = mensagem;
    }

    public String getMensagem(){
        return mensagem;
    }
}
