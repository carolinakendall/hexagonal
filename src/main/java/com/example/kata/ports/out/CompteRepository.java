package com.example.kata.ports.out;

import com.example.kata.domain.model.CompteBancaire;
import org.springframework.stereotype.Repository;

/**
 * port out pour accéder à la persistance
 */
public interface CompteRepository {
    CompteBancaire findByNumeroCompte(String numeroCompte);
    void save(CompteBancaire compte);
}
