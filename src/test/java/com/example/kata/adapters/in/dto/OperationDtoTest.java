package com.example.kata.adapters.in.dto;

import com.example.kata.domain.model.Operation;
import com.example.kata.domain.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationDtoTest {

    @Test
    void testMapListOperationToOperationDtoCasPassant() {
        double montant = 100.0;
        Operation operation = Operation.depot(montant);

        OperationDto dto = OperationDto.mapListOperationToOperationDto(operation);

        assertNotNull(dto);
        assertEquals(100.0, dto.getMontant());
        assertEquals(TypeOperationEnum.DEPOT, dto.getTypeOperationEnum());
        assertNotNull(dto.getDate());
    }
}
