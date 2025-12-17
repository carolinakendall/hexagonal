package com.example.kata.domain.model;

import com.example.kata.domain.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivretEpargneTest {

    @Test
    void testDeposerCasPassant() {
        LivretEpargne livret = new LivretEpargne("123", 0, 1000);

        livret.deposer(500);

        assertEquals(500, livret.getSolde());
        assertEquals(1, livret.getOperations().size());
        assertEquals(500, livret.getOperations().get(0).getMontant());
        assertEquals(TypeOperationEnum.DEPOT, livret.getOperations().get(0).getType());

    }

    @Test
    void testDeposerCasNonPassantValeursInvalides() {
        LivretEpargne livret = new LivretEpargne("123", 0, 1000);

        try {
            livret.deposer(0);
            fail("doit lever une exception car le montant à deposer doit être différent de 0");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant à déposer doit être positif", e.getMessage());
        }

        try {
            livret.deposer(-100);
            fail("doit lever une exception car le montant à deposer doit être positif");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant à déposer doit être positif", e.getMessage());
        }
    }

    @Test
    void testDeposerCasNonPassantDepassementPlafond() {
        LivretEpargne livret = new LivretEpargne("123", 0, 1000);
        livret.deposer(800);

        try {
            livret.deposer(300);
            fail("doit lever une exception car le montant deposé dépasserait le plafond");
        } catch (IllegalStateException e) {
            assertEquals("montant du plafond dépassé", e.getMessage());
        }
    }

    @Test
    void testRetirerCasPassant() {
        LivretEpargne livret = new LivretEpargne("123", 0, 1000);

        livret.deposer(500);

        livret.retirer(200);
        assertEquals(300, livret.getSolde());
        assertEquals(2, livret.getOperations().size());
        assertEquals(200, livret.getOperations().get(1).getMontant());
        assertEquals(TypeOperationEnum.RETRAIT, livret.getOperations().get(1).getType());
    }

    @Test
    void testRetirerCasNonPassantValeursInvalides() {
        LivretEpargne livret = new LivretEpargne("123", 0, 1000);
        livret.deposer(500);

        try {
            livret.retirer(0);
            fail("devrait lever une exception car montant à retirer est 0");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant à retirer doit être positif", e.getMessage());
        }

        try {
            livret.retirer(-50);
            fail("devrait lever une exception car montant à retirer est négatif");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant à retirer doit être positif", e.getMessage());
        }
    }

    @Test
    void testRetirerCasNonPassantSoldeInsufisant() {
        LivretEpargne livret = new LivretEpargne("123", 0, 1000);
        livret.deposer(500);

        try {
            livret.retirer(600);
            fail("devrait lever une exception car le solde est insuffisant");
        } catch (IllegalStateException e) {
            assertEquals("solde insuffisant", e.getMessage());
        }
    }
}
