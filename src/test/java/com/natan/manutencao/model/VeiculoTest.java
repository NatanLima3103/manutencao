package com.natan.manutencao.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VeiculoTest {

    @Test
    void atualizarKm_recusaKmMenorQueAtual(){
        Veiculo v = new Veiculo("Symbol", 2012, "AWA-8F14", 156000);

        boolean atualizou = v.atualizarKm(154000);

        assertFalse(atualizou);
        assertEquals(156000, v.getKmAtual());
    }

    @Test
    void atualizarKm_aceitaKmMaiorQueAtual(){
        Veiculo v = new Veiculo("Symbol", 2012, "AWA-8F14", 156000);

        boolean atualizou = v.atualizarKm(158000);

        assertTrue(atualizou);
        assertEquals(158000, v.getKmAtual());
    }

    @Test
    void atualizarKm_aceitaKmIgual(){
        Veiculo v = new Veiculo("Symbol", 2012, "AWA-8F14", 156000);

        boolean atualizou = v.atualizarKm(156000);

        assertTrue(atualizou);
        assertEquals(156000, v.getKmAtual());
    }
}
