package com.example.kata.domain.model;

import com.example.kata.domain.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationTest {

    @Test
    void testCreationOperationSuiteDepotCasPassant() {

        Operation op = Operation.depot(100.0);

        assertNotNull(op.getDate());
        assertEquals(TypeOperationEnum.DEPOT, op.getType());
        assertEquals(100.0, op.getMontant());
    }

    @Test
    void testCreationOperationSuiteRetraitCasPassant() {

        Operation op = Operation.retrait(50.0);

        assertNotNull(op.getDate());
        assertEquals(TypeOperationEnum.RETRAIT, op.getType());
        assertEquals(50.0, op.getMontant());
    }

    @Test
    void testOperationCasNonPassantSuiteDepotMontantZero() {
        try {
            Operation.depot(0);
            fail("devrait lever un exception car le montant est 0");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant doit être positif", e.getMessage());
        }
    }


    @Test
    void testOperationCasNonPassantSuiteRetraitMontantNegatif() {
        try {
            Operation.retrait(-10);
            fail("devrait lever un exception car le montant est négatif");
        } catch (IllegalArgumentException e) {
            assertEquals("le montant doit être positif", e.getMessage());
        }
    }

}
