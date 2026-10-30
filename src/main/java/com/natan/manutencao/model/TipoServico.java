package com.natan.manutencao.model;

public enum TipoServico {

    TROCA_OLEO("Troca de óleo do motor", 10000, 12),
    FILTRO_OLEO("Filtro de óleo", 10000, 12),
    FILTRO_AR("Filtro de ar do motor", 20000, 24),
    FILTRO_COMBUSTIVEL("Filtro de combustível", 30000, 36),
    FILTRO_CABINE("Filtro de cabine (ar-condicionado)", 15000, 12),
    VELAS("Velas de ignição", 30000, 36),
    CORREIA_DENTADA("Correia dentada", 60000, 60),
    FLUIDO_FREIO("Fluido de freio", 40000, 24);

    private final String descricao;
    private final int intervaloKm;
    private final int intervaloMeses;

    TipoServico(String descricao, int intervaloKm, int intervaloMeses){

        this.descricao = descricao;
        this.intervaloKm = intervaloKm;
        this.intervaloMeses = intervaloMeses;

    }

    public String getDescricao() {
        return descricao;
    }

    public int getIntervaloKm() {
        return intervaloKm;
    }

    public int getIntervaloMeses() {
        return intervaloMeses;
    }
}
