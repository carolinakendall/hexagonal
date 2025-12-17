package com.example.kata.ports.in;

import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.domain.model.CompteCourant;
import com.example.kata.domain.model.ReleveCompte;
import com.example.kata.domain.model.enums.TypeCompteEnum;
import com.example.kata.domain.application.ReleveCompteService;
import com.example.kata.ports.out.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompteLectureServiceTest {
    @Mock
    private CompteRepository compteRepository;
    @Mock
    private ReleveCompteService releveCompteService;

    @InjectMocks
    private CompteLectureService compteLectureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetReleveCasPassant() {
        String numeroCompte = "123";
        CompteBancaire compte = new CompteCourant(numeroCompte, 1000.0, 0, false);
        ReleveCompte releveCompte = new ReleveCompte(TypeCompteEnum.COMPTE_COURANT, 1000.0, new ArrayList<>());

        when(compteRepository.findByNumeroCompte(numeroCompte)).thenReturn(compte);
        when(releveCompteService.genererReleve(compte)).thenReturn(releveCompte);

        ReleveCompte result = compteLectureService.getReleve(numeroCompte);

        assertNotNull(result);
        assertEquals(TypeCompteEnum.COMPTE_COURANT, result.getEnumTypeCompte());
        assertEquals(1000.0, result.getSoldeParDate());
        assertEquals(0, result.getOperations().size());

        verify(compteRepository, times(1)).findByNumeroCompte(numeroCompte);
        verify(releveCompteService, times(1)).genererReleve(compte);
    }

    @Test
    void testGetReleveCompteCasNonPassant() {
        String numeroCompte = "999";
        when(compteRepository.findByNumeroCompte(numeroCompte))
                .thenThrow(new IllegalArgumentException("Compte introuvable"));

        try {
            compteLectureService.getReleve(numeroCompte);
            fail("devrait lever une exception car le compte est introuvable");
        } catch (IllegalArgumentException e) {
            assertEquals("Compte introuvable", e.getMessage());
        }

        verify(compteRepository, times(1)).findByNumeroCompte(numeroCompte);
        verifyNoInteractions(releveCompteService);
    }
}
