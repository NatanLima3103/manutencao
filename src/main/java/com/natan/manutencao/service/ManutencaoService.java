package com.natan.manutencao.service;

import com.natan.manutencao.model.RegistroManutencao;
import com.natan.manutencao.model.TipoServico;
import com.natan.manutencao.model.Veiculo;
import com.natan.manutencao.repository.ManutencaoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ManutencaoService {

    private final Veiculo veiculo;
    private final ManutencaoRepository repository;

    public ManutencaoService(Veiculo veiculo, ManutencaoRepository repository){
        this.veiculo = veiculo;
        this.repository = repository;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public boolean atualizarKm(int novoKm){
        return veiculo.atualizarKm(novoKm);
    }

    public ResultadoRegistro registrarManutencao(TipoServico tipo, LocalDate data, int kmNoServico,
                                                 BigDecimal custo, String observacao, LocalDate hoje) {

        if (kmNoServico < 0){
            return ResultadoRegistro.KM_INVALIDO;
        }

        if (custo == null || custo.compareTo(BigDecimal.ZERO) < 0){
            return ResultadoRegistro.CUSTO_INVALIDO;
        }

        if (data.isAfter(hoje)){
            return ResultadoRegistro.DATA_FUTURA;
        }

        RegistroManutencao registro = new RegistroManutencao(tipo, data, kmNoServico, custo, observacao);
        repository.salvar(registro);

        if (kmNoServico > veiculo.getKmAtual()) {
            veiculo.atualizarKm(kmNoServico);
        }

        return ResultadoRegistro.OK;
    }
}
