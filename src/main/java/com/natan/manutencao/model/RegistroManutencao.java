package com.natan.manutencao.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RegistroManutencao {

    private long id;
    private final TipoServico tipo;
    private final LocalDate data;
    private final int kmNoServico;
    private final BigDecimal custo;
    private final String observacao;

    public RegistroManutencao(TipoServico tipo, LocalDate data, int kmNoServico, BigDecimal custo, String observacao){

        this.tipo = tipo;
        this.data = data;
        this.kmNoServico = kmNoServico;
        this.custo = custo;
        this.observacao = (observacao == null) ? "" : observacao;
    }

    public long getId() {
        return id;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getKmNoServico() {
        return kmNoServico;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getProximoKm(){
        return kmNoServico + tipo.getIntervaloKm();
    }

    public LocalDate getProximaData(){
        return data.plusMonths(tipo.getIntervaloMeses());
    }
}
