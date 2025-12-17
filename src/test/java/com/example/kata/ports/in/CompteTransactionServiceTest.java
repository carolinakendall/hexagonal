package com.example.kata.ports.in;

import com.example.kata.adapters.in.dto.CreerCompteCourantDto;
import com.example.kata.adapters.in.dto.CreerLivretDto;
import com.example.kata.domain.model.CompteBancaire;
import com.example.kata.domain.model.CompteCourant;
import com.example.kata.domain.model.LivretEpargne;
import com.example.kata.ports.out.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompteTransactionServiceTest {

    @Mock
    private CompteRepository compteRepository;

    @InjectMocks
    private CompteTransactionService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeposerCasPassant() {
        String numeroCompte = "123";
        CompteBancaire compte = mock(CompteBancaire.class);
        when(compteRepository.findByNumeroCompte(numeroCompte)).thenReturn(compte);

        service.deposer(numeroCompte, 100);

        verify(compteRepository, times(1)).findByNumeroCompte(numeroCompte);
        verify(compte, times(1)).deposer(100);
        verify(compteRepository, times(1)).saveOrUpdate(compte);
    }

    @Test
    void testRetirerCasPassant() {
        String numeroCompte = "123";
        CompteBancaire compte = mock(CompteBancaire.class);
        when(compteRepository.findByNumeroCompte(numeroCompte)).thenReturn(compte);

        service.retirer(numeroCompte, 50);

        verify(compteRepository, times(1)).findByNumeroCompte(numeroCompte);
        verify(compte, times(1)).retirer(50);
        verify(compteRepository, times(1)).saveOrUpdate(compte);
    }

    @Test
    void testCreerCompteCourantCasPassant() {
        CreerCompteCourantDto request = new CreerCompteCourantDto();
        request.setNumeroCompte("456");
        request.setDecouvertAutorise(1000);
        request.setIndicateurDecouvertAutorise(true);

        service.creerCompteCourant(request);

        ArgumentCaptor<CompteCourant> captor = ArgumentCaptor.forClass(CompteCourant.class);
        verify(compteRepository, times(1)).saveOrUpdate(captor.capture());

        CompteCourant savedCompte = captor.getValue();
        assertEquals("456", savedCompte.getNumeroCompte());
        assertEquals(1000, savedCompte.getDecouvertAutorise());
        assertTrue(savedCompte.isIndicateurDecouvertAutorise());
        assertEquals(0, savedCompte.getSolde());
    }

    @Test
    void testCreerLivretCasPassant() {
        CreerLivretDto request = new CreerLivretDto();
        request.setNumeroCompte("789");
        request.setPlafond(5000);

        service.creerLivret(request);

        ArgumentCaptor<LivretEpargne> captor = ArgumentCaptor.forClass(LivretEpargne.class);
        verify(compteRepository, times(1)).saveOrUpdate(captor.capture());

        LivretEpargne savedLivret = captor.getValue();
        assertEquals("789", savedLivret.getNumeroCompte());
        assertEquals(0, savedLivret.getSolde());
        assertEquals(5000, savedLivret.getPlafond());
    }
}
