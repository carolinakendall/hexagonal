package com.example.kata.ports.in;

import com.example.kata.adapters.in.dto.CreerCompteCourantDto;
import com.example.kata.adapters.in.dto.CreerLivretDto;
import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.domain.model.CompteCourant;
import com.example.kata.domain.model.LivretEpargne;
import com.example.kata.ports.out.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteTransactionService implements CompteTransaction {

    @Autowired
    private CompteRepository compteRepository;

    public CompteTransactionService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public void deposer(String numeroCompte, double montant) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte);
        compte.deposer(montant);
        compteRepository.save(compte);
    }

    @Override
    public void retirer(String numeroCompte, double montant) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte);
        compte.retirer(montant);
        compteRepository.save(compte);
    }

    @Override
    public void creerCompteCourant(CreerCompteCourantDto request) {
        CompteCourant compte = new CompteCourant(
                request.getNumeroCompte(),
                request.getDecouvertAutorise(),
                request.isIndicateurDecouvertAutorise()
        );
        compteRepository.save(compte);
    }

    @Override
    public void creerLivret(CreerLivretDto request) {
        LivretEpargne livret = new LivretEpargne(
                request.getNumeroCompte(),
                request.getPlafond()
        );
        compteRepository.save(livret);
    }
}
