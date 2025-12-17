package com.example.kata.adapters.out;

import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.domain.model.CompteCourant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CompteInMemoryAdapterTest {

    @Test
    void testSaveAndFindByNumeroCompteCasPassant() {
        CompteInMemoryAdapter adapter = new CompteInMemoryAdapter();
        CompteBancaire compte = new CompteCourant("123", 1000.0, 2000, false);

        adapter.saveOrUpdate(compte);

        CompteCourant resultat = (CompteCourant) adapter.findByNumeroCompte("123");

        assertNotNull(resultat);
        assertEquals("123", resultat.getNumeroCompte());
        assertEquals(1000.0, resultat.getSolde());
        assertEquals(2000.0, resultat.getDecouvertAutorise());
        assertFalse(resultat.isIndicateurDecouvertAutorise());
    }

    @Test
    void testUpdateAndFindByNumeroCompteCasPassant() {
        CompteInMemoryAdapter adapter = new CompteInMemoryAdapter();
        CompteBancaire compte = new CompteCourant("123", 1000.0, 2000,false);
        adapter.saveOrUpdate(compte);

        CompteBancaire updatedCompte = new CompteCourant("123", 2000.0, 2000,false);
        adapter.saveOrUpdate(updatedCompte);

        CompteBancaire resultat = adapter.findByNumeroCompte("123");

        assertEquals(2000.0, resultat.getSolde());
    }

    @Test
    void testFindByNumeroCompteCasNonPassant() {
        CompteInMemoryAdapter adapter = new CompteInMemoryAdapter();

        try {
            adapter.findByNumeroCompte("999");
            fail("on devrait lever une exception car numero de compte manquant");
        } catch (IllegalArgumentException e) {
            assertEquals("Compte introuvable", e.getMessage());
        }
    }

}
