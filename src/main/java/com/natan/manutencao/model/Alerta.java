package com.natan.manutencao.model;

import java.time.LocalDate;

public class Alerta {

    private final TipoServico tipo;
    private final int proximoKm;
    private final LocalDate proximaData;
    private final int kmRestantes;
    private final long diasRestantes;
    private final StatusAlerta status;

    public Alerta(TipoServico tipo, int proximoKm, LocalDate proximaData, int kmRestantes, long diasRestantes, StatusAlerta status){
        this.tipo = tipo;
        this.proximoKm = proximoKm;
        this.proximaData = proximaData;
        this.kmRestantes = kmRestantes;
        this.diasRestantes = diasRestantes;
        this.status = status;
    }

    public TipoServico getTipo() {
        return tipo;
    }

    public int getProximoKm() {
        return proximoKm;
    }

    public LocalDate getProximaData() {
        return proximaData;
    }

    public int getKmRestantes() {
        return kmRestantes;
    }

    public long getDiasRestantes() {
        return diasRestantes;
    }

    public StatusAlerta getStatus() {
        return status;
    }
}
