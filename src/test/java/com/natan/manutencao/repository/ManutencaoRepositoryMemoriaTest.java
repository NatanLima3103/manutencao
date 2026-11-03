package com.natan.manutencao.repository;

import com.natan.manutencao.model.RegistroManutencao;
import com.natan.manutencao.model.TipoServico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManutencaoRepositoryMemoriaTest {

    private ManutencaoRepositoryMemoria repo;
    private RegistroManutencao a;
    private RegistroManutencao b;
    private RegistroManutencao c;
    private RegistroManutencao d;

    @BeforeEach
    void setUp() {
        repo = new ManutencaoRepositoryMemoria();
        a = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000, new BigDecimal("250.00"), "");
        b = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2025, 10, 20), 75000, new BigDecimal("250.00"), "");
        c = new RegistroManutencao(
                TipoServico.FILTRO_AR, LocalDate.of(2024, 11, 15), 70000, new BigDecimal("80.00"), "");
        d = new RegistroManutencao(
                TipoServico.FILTRO_AR, LocalDate.of(2024, 11, 15), 72000, new BigDecimal("80.00"), "");
    }

    @Test
    void salvar_registrosNovos_recebemIdsSequenciais() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        assertEquals(1, a.getId());
        assertEquals(2, b.getId());
        assertEquals(3, c.getId());
    }

    @Test
    void listarTodos_mantemOrdemDeSalvamento() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        List<RegistroManutencao> lista = repo.listarTodos();

        assertEquals(3, lista.size());
        assertSame(a, lista.get(0));
        assertSame(b, lista.get(1));
        assertSame(c, lista.get(2));
    }

    @Test
    void listarPorTipo_devolveSoDoTipo() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        List<RegistroManutencao> lista = repo.listarPorTipo(TipoServico.TROCA_OLEO);

        assertEquals(2, lista.size());
        assertSame(a, lista.get(0));
        assertSame(b, lista.get(1));
    }

    @Test
    void listarPorTipo_semRegistros_devolveListaVazia() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        List<RegistroManutencao> lista = repo.listarPorTipo(TipoServico.VELAS);

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    @Test
    void buscarUltimoPorTipo_devolveOMaisRecentePorData() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        assertSame(a, repo.buscarUltimoPorTipo(TipoServico.TROCA_OLEO));
    }

    @Test
    void buscarUltimoPorTipo_semRegistros_devolveNull() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        assertNull(repo.buscarUltimoPorTipo(TipoServico.VELAS));
    }

    @Test
    void buscarUltimoPorTipo_empateDeData_devolveMaiorKm() {
        repo.salvar(c);
        repo.salvar(d);

        assertSame(d, repo.buscarUltimoPorTipo(TipoServico.FILTRO_AR));
    }

    @Test
    void listarTodos_devolveCopia() {
        repo.salvar(a);
        repo.salvar(b);
        repo.salvar(c);

        repo.listarTodos().clear();

        assertEquals(3, repo.listarTodos().size());
    }
}