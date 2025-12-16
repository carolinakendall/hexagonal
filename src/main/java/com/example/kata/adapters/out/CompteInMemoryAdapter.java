package com.example.kata.adapters.out;

import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.ports.out.CompteRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter out qui implémente le port pour la persistance CompteRepository
 */
@Repository
public class CompteInMemoryAdapter implements CompteRepository {

    private final Map<String, CompteBancaire> stockage = new HashMap<>();

    @Override
    public CompteBancaire findByNumeroCompte(String numeroCompte) {
        CompteBancaire compte = stockage.get(numeroCompte);
        if (compte == null) throw new IllegalArgumentException("Compte introuvable");
        return compte;
    }

    @Override
    public void save(CompteBancaire compte) {
        stockage.put(compte.getNumeroCompte(), compte);
    }
}
