package com.example.kata.ports.in;

import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.domain.model.ReleveCompte;
import com.example.kata.domain.application.ReleveCompteService;
import com.example.kata.ports.out.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompteLectureService implements CompteLecture {

    @Autowired
    private final CompteRepository compteRepository;
    private final ReleveCompteService releveCompteService;

    public CompteLectureService(CompteRepository compteRepository, ReleveCompteService releveCompteService) {
        this.compteRepository = compteRepository;
        this.releveCompteService = releveCompteService;
    }
    @Override
    public ReleveCompte getReleve(String numeroCompte) {
        CompteBancaire compte = compteRepository.findByNumeroCompte(numeroCompte);
        return releveCompteService.genererReleve(compte);
    }
}