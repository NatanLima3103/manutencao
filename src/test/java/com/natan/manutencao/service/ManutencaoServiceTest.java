package com.natan.manutencao.service;

import com.natan.manutencao.model.TipoServico;
import com.natan.manutencao.model.Veiculo;
import com.natan.manutencao.repository.ManutencaoRepository;
import com.natan.manutencao.repository.ManutencaoRepositoryMemoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ManutencaoServiceTest {

    private ManutencaoRepository repo;
    private Veiculo veiculo;
    private ManutencaoService service;
    private LocalDate hoje;

    @BeforeEach
    void setUp() {
        repo = new ManutencaoRepositoryMemoria();
        veiculo = new Veiculo("Renault Symbol 1.6 16v", 2012, "ABC1D23", 84200);
        service = new ManutencaoService(veiculo, repo);
        hoje = LocalDate.of(2026, 10, 30);
    }

    @Test
    void registrarManutencao_dadosValidos_devolveOkESalva() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000, new BigDecimal("250.00"), "", hoje);

        assertEquals(ResultadoRegistro.OK, resultado);
        assertEquals(1, repo.listarTodos().size());
    }

    @Test
    void registrarManutencao_kmMaiorQueAtual_atualizaKmDoVeiculo() {
        service.registrarManutencao(TipoServico.FILTRO_AR, LocalDate.of(2026, 10, 30), 85000, new BigDecimal("89.90"), "", hoje);

        assertEquals(85000, veiculo.getKmAtual());
    }

    @Test
    void registrarManutencao_kmMenorQueAtual_naoMudaKmDoVeiculo() {
        service.registrarManutencao(TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000, new BigDecimal("250.00"), "", hoje);

        assertEquals(84200, veiculo.getKmAtual());
    }

    @Test
    void registrarManutencao_dataIgualAHoje_devolveOk() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.FILTRO_AR, hoje, 85000, new BigDecimal("89.90"), "", hoje);

        assertEquals(ResultadoRegistro.OK, resultado);
    }

    @Test
    void registrarManutencao_dataFutura_devolveDataFuturaENaoSalva() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.FILTRO_OLEO, LocalDate.of(2026, 10, 31), 85000, new BigDecimal("50.00"), "", hoje);

        assertEquals(ResultadoRegistro.DATA_FUTURA, resultado);
        assertTrue(repo.listarTodos().isEmpty());
    }

    @Test
    void registrarManutencao_kmNegativo_devolveKmInvalidoENaoSalva() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.VELAS, LocalDate.of(2026, 10, 1), -1, new BigDecimal("50.00"), "", hoje);

        assertEquals(ResultadoRegistro.KM_INVALIDO, resultado);
        assertTrue(repo.listarTodos().isEmpty());
    }

    @Test
    void registrarManutencao_custoNegativo_devolveCustoInvalidoENaoSalva() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.VELAS, LocalDate.of(2026, 10, 1), 85000, new BigDecimal("-10.00"), "", hoje);

        assertEquals(ResultadoRegistro.CUSTO_INVALIDO, resultado);
        assertTrue(repo.listarTodos().isEmpty());
    }

    @Test
    void registrarManutencao_custoNull_devolveCustoInvalido() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.VELAS, LocalDate.of(2026, 10, 1), 85000, null, "", hoje);

        assertEquals(ResultadoRegistro.CUSTO_INVALIDO, resultado);
    }

    @Test
    void registrarManutencao_doisErros_valeOPrimeiroDaOrdem() {
        ResultadoRegistro resultado = service.registrarManutencao(TipoServico.VELAS, LocalDate.of(2026, 10, 31), -1, new BigDecimal("50.00"), "", hoje);

        assertEquals(ResultadoRegistro.KM_INVALIDO, resultado);
    }

    @Test
    void registrarManutencao_falha_naoAlteraKmDoVeiculo() {
        service.registrarManutencao(TipoServico.VELAS, LocalDate.of(2026, 10, 1), 90000, new BigDecimal("-10.00"), "", hoje);

        assertEquals(84200, veiculo.getKmAtual());
    }

    @Test
    void atualizarKm_kmMenor_recusa() {
        boolean atualizou = service.atualizarKm(80000);

        assertFalse(atualizou);
        assertEquals(84200, veiculo.getKmAtual());
    }

    @Test
    void atualizarKm_kmMaior_aceita() {
        boolean atualizou = service.atualizarKm(86000);

        assertTrue(atualizou);
        assertEquals(86000, veiculo.getKmAtual());
    }
}
