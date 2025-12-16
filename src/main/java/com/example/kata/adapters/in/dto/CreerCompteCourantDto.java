package com.example.kata.adapters.in.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representant un compte courant
 */
@Getter
@Setter
@NoArgsConstructor
public class CreerCompteCourantDto {

    private String numeroCompte;

    private double decouvertAutorise;

    private boolean indicateurDecouvertAutorise;

}
