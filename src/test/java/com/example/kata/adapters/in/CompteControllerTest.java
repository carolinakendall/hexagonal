package com.example.kata.adapters.in;
import com.example.kata.adapters.in.dto.CreerCompteCourantDto;
import com.example.kata.adapters.in.dto.CreerLivretDto;
import com.example.kata.adapters.in.dto.OperationDto;
import com.example.kata.adapters.in.dto.ReleveDto;
import com.example.kata.domain.model.Operation;
import com.example.kata.domain.model.ReleveCompte;
import com.example.kata.domain.model.enums.TypeCompteEnum;
import com.example.kata.ports.in.CompteLecture;
import com.example.kata.ports.in.CompteTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CompteControllerTest {

    @Mock
    private CompteTransaction compteTransaction;

    @Mock
    private CompteLecture compteLecture;

    @InjectMocks
    private CompteController compteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testRetirerCasPassant() {
        String numeroCompte = "123";
        OperationDto dto = new OperationDto();
        dto.setMontant(50);

        compteController.retirer(numeroCompte, dto);

        verify(compteTransaction, times(1)).retirer(numeroCompte, 50);
    }

    @Test
    void testGetReleveCasPassant() {
        String numeroCompte = "123";
        ReleveCompte releveCompte = new ReleveCompte(
                TypeCompteEnum.COMPTE_COURANT,
                1000.0,
                List.of(Operation.depot(100), Operation.retrait(50))
        );

        when(compteLecture.getReleve(numeroCompte)).thenReturn(releveCompte);

        ReleveDto releveDto = compteController.getReleve(numeroCompte);

        assertNotNull(releveDto);
        assertEquals("COMPTE_COURANT", releveDto.getTypeCompte());
        assertEquals(1000.0, releveDto.getSolde());
        assertEquals(2, releveDto.getOperations().size());

        verify(compteLecture, times(1)).getReleve(numeroCompte);
    }

    @Test
    void testCreerCompteCourantCasPassant() {
        CreerCompteCourantDto dto = new CreerCompteCourantDto();

        compteController.creerCompteCourant(dto);

        verify(compteTransaction, times(1)).creerCompteCourant(dto);
    }

    @Test
    void testCreerLivretCasPassant() {
        CreerLivretDto dto = new CreerLivretDto();

        compteController.creerLivret(dto);

        verify(compteTransaction, times(1)).creerLivret(dto);
    }
}
