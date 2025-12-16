package com.example.kata.ports.in;

import com.example.kata.adapters.in.dto.CreerCompteCourantDto;
import com.example.kata.adapters.in.dto.CreerLivretDto;

/**
 * depot,retrait,creation
 */
public interface CompteTransaction {
    void deposer(String numeroCompte, double montant);
    void retirer(String numeroCompte, double montant);
    void creerCompteCourant(CreerCompteCourantDto request);
    void creerLivret(CreerLivretDto request);
}