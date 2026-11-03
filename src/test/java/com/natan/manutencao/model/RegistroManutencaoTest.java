package com.natan.manutencao.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RegistroManutencaoTest {

    @Test
    void getProximoKm_trocaOleo_somaIntervaloDoTipo() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000,
                new BigDecimal("250.00"), "");

        assertEquals(88000, r.getProximoKm());
    }

    @Test
    void getProximaData_trocaOleo_somaDozeMeses() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000,
                new BigDecimal("250.00"), "");

        assertEquals(LocalDate.of(2027, 3, 15), r.getProximaData());
    }

    @Test
    void getProximaData_anoBissexto_ajustaUltimoDia() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2024, 2, 29), 50000,
                new BigDecimal("250.00"), "");

        assertEquals(LocalDate.of(2025, 2, 28), r.getProximaData());
    }

    @Test
    void getProximoKmEProximaData_correiaDentada() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.CORREIA_DENTADA, LocalDate.of(2023, 1, 10), 24000,
                new BigDecimal("900.00"), "");

        assertEquals(84000, r.getProximoKm());
        assertEquals(LocalDate.of(2028, 1, 10), r.getProximaData());
    }

    @Test
    void construtor_observacaoNull_guardaTextoVazio() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000,
                new BigDecimal("250.00"), null);

        assertEquals("", r.getObservacao());
    }

    @Test
    void construtor_novoRegistro_comecaComIdZero() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000,
                new BigDecimal("250.00"), "");

        assertEquals(0, r.getId());
    }

    @Test
    void setId_guardaOId() {
        RegistroManutencao r = new RegistroManutencao(
                TipoServico.TROCA_OLEO, LocalDate.of(2026, 3, 15), 78000,
                new BigDecimal("250.00"), "");

        r.setId(5);

        assertEquals(5, r.getId());
    }
}