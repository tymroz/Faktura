package com.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class Test {

    private Faktura faktura;

    @BeforeEach
    void setUp() {
        faktura = new Faktura("Klient Testowy");
    }

    @org.junit.jupiter.api.Test
    void dodajElementPowinienDodacElementDoFaktury() {
        FakturaElement element = new FakturaElement("Testowy Artykuł", 3, 10.0);
        faktura.dodajElement(element);

        assertEquals(1, faktura.getElementy().size());
    }

    @org.junit.jupiter.api.Test
    void obliczCalkowityKosztPowinienZwrocicPoprawnyKoszt() {
        faktura.dodajElement(new FakturaElement("Artykuł 1", 2, 20.0));
        faktura.dodajElement(new FakturaElement("Artykuł 2", 3, 15.0));

        assertEquals(2 * 20.0 + 3 * 15.0, faktura.obliczCalkowityKoszt(), 0.01);
    }
}
