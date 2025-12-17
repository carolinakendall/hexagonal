package com.example.kata.domain.model.enums;
import com.example.kata.domain.model.enums.TypeCompteEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TypeCompteEnumTest {

    @Test
    void testGetEnumFromLibelleCasPassant() {
        Assertions.assertEquals(TypeCompteEnum.COMPTE_COURANT, TypeCompteEnum.getEnumFromLibelle("CompteCourant"));
        assertEquals(TypeCompteEnum.LIVRET_EPARGNE, TypeCompteEnum.getEnumFromLibelle("LivretEpargne"));
    }

    @Test
    void testGetEnumFromLibelleCasNonPassant() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TypeCompteEnum.getEnumFromLibelle("CompteInconnu")
        );
        assertEquals("Type de compte inconnu : CompteInconnu", exception.getMessage());
    }
}
