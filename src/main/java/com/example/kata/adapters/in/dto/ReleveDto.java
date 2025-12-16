package com.example.kata.adapters.in.dto;

import com.example.kata.domain.model.Operation;
import com.example.kata.domain.model.ReleveCompte;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO pour exposer une opération dans un relevé de compte
 */
@Getter
@Setter
@AllArgsConstructor
public class ReleveDto {

    private String typeCompte;

    private double solde;

    private List<OperationDto> operations;

    public static ReleveDto mapReleveCompteToReleveDto(ReleveCompte releve) {
        return new ReleveDto(
                releve.getEnumTypeCompte().name(),
                releve.getSoldeParDate(),
                releve.getOperations().stream()
                        .map(OperationDto::mapListOperationToOperationDto)
                        .toList()
        );
    }

}
