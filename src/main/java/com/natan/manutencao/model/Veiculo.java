package com.natan.manutencao.model;

public class Veiculo {

    private final String modelo;
    private final int anoFabricacao;
    private final String placa;
    private int kmAtual;

    public Veiculo(String modelo, int anoFabricacao, String placa, int kmInicial){
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.placa = placa;
        this.kmAtual = kmInicial;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getPlaca() {
        return placa;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public boolean atualizarKm(int novoKm){
        if (kmAtual <= novoKm){
            kmAtual = novoKm;
            return true;
        }
        return false;
    }
}
