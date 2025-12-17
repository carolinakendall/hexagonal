package com.example.kata.adapters.in.dto;

import com.example.kata.domain.model.Operation;
import com.example.kata.domain.model.ReleveCompte;
import com.example.kata.domain.model.enums.TypeCompteEnum;
import com.example.kata.domain.model.enums.TypeOperationEnum;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ReleveDtoTest {

    @Test
    void mapReleveCompteToReleveDtoCasPassant() {
        Operation depot = Operation.depot(100);
        Operation retrait = Operation.retrait(50);

        ReleveCompte releveCompte = new ReleveCompte(
                TypeCompteEnum.COMPTE_COURANT,
                1000.0,
                List.of(depot, retrait)
        );

        ReleveDto dto = ReleveDto.mapReleveCompteToReleveDto(releveCompte);

        assertNotNull(dto);

        assertEquals("COMPTE_COURANT", dto.getTypeCompte());
        assertEquals(1000.0, dto.getSolde());
        assertEquals(2, dto.getOperations().size());
        assertEquals(100, dto.getOperations().get(0).getMontant());
        assertEquals(TypeOperationEnum.DEPOT, dto.getOperations().get(0).getTypeOperationEnum());
        assertNotNull(dto.getOperations().get(0).getDate());
        OperationDto op2 = dto.getOperations().get(1);
        assertEquals(50, dto.getOperations().get(1).getMontant());
        assertEquals(TypeOperationEnum.RETRAIT, dto.getOperations().get(1).getTypeOperationEnum());
        assertNotNull(dto.getOperations().get(1).getDate());
    }
}
