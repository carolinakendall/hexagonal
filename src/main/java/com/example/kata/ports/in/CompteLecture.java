package com.example.kata.ports.in;
/**
 * lecture releve solde
 */
import com.example.kata.domain.model.ReleveCompte;

public interface CompteLecture {
    ReleveCompte getReleve(String numeroCompte);
}
