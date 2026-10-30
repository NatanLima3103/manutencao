package com.natan.manutencao.model;

public enum StatusAlerta {

    VENCIDO ("VENCIDO"),
    PROXIMO("PRÓXIMO"),
    EM_DIA("EM DIA");

    private final String rotulo;

    StatusAlerta(String rotulo){
        this.rotulo = rotulo;
    }

    public String getRotulo(){
        return rotulo;
    }
}
