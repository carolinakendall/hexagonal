package com.example.kata.domain.model;

import com.example.kata.domain.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteCourantTest {

    @Test
    void testDeposerCasPassant() {
        CompteCourant compte = new CompteCourant("123", 500, 100, false);

        compte.deposer(200);

        assertEquals(700, compte.getSolde());
        assertEquals(1, compte.getOperations().size());
        assertEquals(200, compte.getOperations().get(0).getMontant());
        assertEquals(TypeOperationEnum.DEPOT, compte.getOperations().get(0).getType());

    }

    @Test
    void testDeposerCasNonPassant() {
        CompteCourant compte = new CompteCourant("123", 500, 100, false);

        try {
            compte.deposer(-50);
            fail("devrait lever une exception car on essaye de deposer un montant négatif");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant à déposer doit être positif", e.getMessage());
        }
    }

    @Test
    void testRetirerCasPassant() {
        CompteCourant compte = new CompteCourant("123", 500, 10, false);
        compte.retirer(200);

        assertEquals(300, compte.getSolde());
        assertEquals(1, compte.getOperations().size());
        assertEquals(200, compte.getOperations().get(0).getMontant());
        assertEquals(TypeOperationEnum.RETRAIT, compte.getOperations().get(0).getType());
    }

    @Test
    void testRetirerCasPassantDecouvert() {
        CompteCourant compte = new CompteCourant("123", 500, 200, true);

        compte.retirer(600);

        assertEquals(-100, compte.getSolde());
        assertEquals(1, compte.getOperations().size());
        assertEquals(600, compte.getOperations().get(0).getMontant());
        assertEquals(TypeOperationEnum.RETRAIT, compte.getOperations().get(0).getType());

    }

    @Test
    void testRetirerCasNonPassantDecouvertDepasse() {
        CompteCourant compte = new CompteCourant("123", 500, 200, true);

        try {
            compte.retirer(800);
            fail("devrait lever une exception car on essaye de retirer un montant au dessus du plafond");
        } catch (IllegalStateException e) {
            assertEquals("montant du découvert dépassé", e.getMessage());
        }
    }

}
