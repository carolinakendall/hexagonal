package com.example.kata.adapters.in.dto;

import com.example.kata.domain.model.Operation;
import com.example.kata.domain.model.enums.TypeOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO representant une operation
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OperationDto {

    private double montant;

    private  LocalDateTime date;

    private  TypeOperationEnum typeOperationEnum;

    public static OperationDto mapListOperationToOperationDto(Operation operation) {
        return new OperationDto(
                operation.getMontant(),
                operation.getDate(),
                operation.getType()
        );
    }
}