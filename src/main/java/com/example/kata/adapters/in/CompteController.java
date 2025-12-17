package com.example.kata.adapters.in;

import com.example.kata.adapters.in.dto.CreerCompteCourantDto;
import com.example.kata.adapters.in.dto.CreerLivretDto;
import com.example.kata.adapters.in.dto.OperationDto;
import com.example.kata.adapters.in.dto.ReleveDto;
import com.example.kata.domain.model.ReleveCompte;
import com.example.kata.ports.in.CompteLecture;
import com.example.kata.ports.in.CompteTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller pour les comptes
 * inbound adapter
 */
@RestController
@RequestMapping("/comptes")
public class CompteController {

   @Autowired
   private CompteTransaction compteTransaction;

   @Autowired
   private CompteLecture compteLecture;

    @PostMapping("/{numeroCompte}/depot")
    public void deposer(@PathVariable String numeroCompte,
                        @RequestBody OperationDto operationDto) {
        compteTransaction.deposer(numeroCompte, operationDto.getMontant());
    }

    @PostMapping("/{numeroCompte}/retrait")
    public void retirer(@PathVariable String numeroCompte,
                        @RequestBody OperationDto operationDto) {
        compteTransaction.retirer(numeroCompte, operationDto.getMontant());
    }

    @GetMapping("/{numeroCompte}/releve")
    public ReleveDto getReleve(@PathVariable String numeroCompte) {
        ReleveCompte releve = compteLecture.getReleve(numeroCompte);
        return ReleveDto.mapReleveCompteToReleveDto(releve);
    }

    @PostMapping("/courant")
    public void creerCompteCourant(@RequestBody CreerCompteCourantDto creerCompteCourantDto) {
        compteTransaction.creerCompteCourant(creerCompteCourantDto);
    }

    @PostMapping("/livret")
    public void creerLivret(@RequestBody CreerLivretDto creerLivretDto) {
        compteTransaction.creerLivret(creerLivretDto);
    }
}