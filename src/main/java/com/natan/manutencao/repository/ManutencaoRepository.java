package com.natan.manutencao.repository;

import com.natan.manutencao.model.RegistroManutencao;
import com.natan.manutencao.model.TipoServico;

import java.util.List;

public interface ManutencaoRepository {

    // Guarda o registro e dá a ele um novo id usando setId
    void salvar(RegistroManutencao registro);

    // Todos os registros, na ordem em que foram salvos, devolve uma cópia da lista.
    List<RegistroManutencao> listarTodos();

    // Só os registros do tipo informado, na ordem em que foram salvos, lista vazia (nunca null) se não houve nenhum.
    List<RegistroManutencao> listarPorTipo(TipoServico tipo);

    // Registro mais recente do tipo informado: maior data; em empate de data, o de maior km. Retorna null se esse tipo ainda não tiver registro
    RegistroManutencao buscarUltimoPorTipo(TipoServico tipo);
}