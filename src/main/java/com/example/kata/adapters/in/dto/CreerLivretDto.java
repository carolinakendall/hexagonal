package com.example.kata.adapters.in.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representant un livret
 */
@Getter
@Setter
@NoArgsConstructor
public class CreerLivretDto {

    private String numeroCompte;

    private double plafond;

}
