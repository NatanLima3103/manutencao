package com.natan.manutencao.repository;

import com.natan.manutencao.model.RegistroManutencao;
import com.natan.manutencao.model.TipoServico;

import java.util.ArrayList;
import java.util.List;

public class ManutencaoRepositoryMemoria implements ManutencaoRepository{

    private final List<RegistroManutencao> registros = new ArrayList<>();
    private long proximoId = 1;

    @Override
    public void salvar(RegistroManutencao registro){
        registro.setId(proximoId);
        registros.add(registro);
        proximoId++;
    }

    @Override
    public List<RegistroManutencao> listarTodos(){
        return new ArrayList<>(registros);
    }

    @Override
    public List<RegistroManutencao> listarPorTipo(TipoServico tipo){
        List<RegistroManutencao> doTipo = new ArrayList<>();
        for (RegistroManutencao r : registros){
            if (r.getTipo() == tipo){
                doTipo.add(r);
            }
        }
        return doTipo;
    }

    @Override
    public RegistroManutencao buscarUltimoPorTipo(TipoServico tipo){
        RegistroManutencao melhor = null;
        for (RegistroManutencao r : registros){
            if (r.getTipo() != tipo){
                continue;
            }
            if (melhor == null){
                melhor = r;
            } else if (r.getData().isAfter(melhor.getData())) {
                melhor = r;
            } else if (r.getData().equals(melhor.getData()) && r.getKmNoServico() > melhor.getKmNoServico()) {
                melhor = r;
            }
        }
        return melhor;
    }
}
