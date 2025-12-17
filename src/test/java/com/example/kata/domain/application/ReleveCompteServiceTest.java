package com.example.kata.domain.application;

import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.domain.model.CompteCourant;
import com.example.kata.domain.model.ReleveCompte;
import com.example.kata.domain.model.enums.TypeCompteEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReleveCompteServiceTest {
    private final ReleveCompteService releveCompteService = new ReleveCompteService();

    @Test
    void testGenererReleveCasPassant() {
        CompteBancaire compte = new CompteCourant("123", 1000.0, 0, false);
        compte.deposer(200);
        compte.retirer(50);

        ReleveCompte releve = releveCompteService.genererReleve(compte);

        assertNotNull(releve);
        assertEquals(TypeCompteEnum.COMPTE_COURANT, releve.getEnumTypeCompte());
        assertEquals(compte.getSolde(), releve.getSoldeParDate());
        assertEquals(compte.getOperations().size(), releve.getOperations().size());
        assertEquals(200, releve.getOperations().get(0).getMontant());

    }

}
