package com.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

/*
Klasa FakturaElement ma odpowiedzialność za przechowywanie informacji o elemencie faktury (nazwa artykułu, ilość i cena) oraz obliczanie kosztu elementu. Jest to przykład wysokiej spójności, ponieważ klasa ma tylko jedną główną odpowiedzialność, która jest związana z jej nazwą.
 */
/*
Klasa FakturaElement nie ma zależności od innych klas. Nie jest powiązana z klasą Faktura ani z klasą Main. Nie ma bezpośrednich odwołań do innych klas, co oznacza niskie sprzężenie.
 */
/*
Klasa FakturaElement ma odpowiedzialność za obliczanie kosztu elementu faktury, co jest zgodne z zasadą "Information Expert". Jest to odpowiednia klasa, aby obliczyć koszt, ponieważ posiada niezbędne dane.
 */
class FakturaElement {
    private final String artykul;
    private final int ilosc;
    private final double cena;

    public FakturaElement(String artykul, int ilosc, double cena) {
        this.artykul = artykul;
        this.ilosc = ilosc;
        this.cena = cena;
    }

    public double obliczKoszt() {
        return ilosc * cena;
    }

    @Override
    public String toString() {
        return artykul + " x " + ilosc + " * " + cena + " PLN = " + obliczKoszt() + " PLN";
    }
}

/*
Klasa Faktura ma odpowiedzialność za przechowywanie danych faktury (klienta i elementów) oraz obliczanie całkowitego kosztu faktury. Jest to także przykład wysokiej spójności, ponieważ klasa zajmuje się tylko operacjami związanymi z fakturą.
 */
/*
Klasa Faktura ma tylko jedną zależność od klasy FakturaElement, która jest uzasadniona, ponieważ Faktura zawiera listę elementów faktury. Nie ma innych zależności od klas zewnętrznych, co utrzymuje niskie sprzężenie.
 */
/*
Klasa Faktura ma odpowiedzialność za obliczanie całkowitego kosztu faktury, zarządzanie elementami faktury oraz wyświetlanie faktury. Te odpowiedzialności są zgodne z zasadami "Controller", "Creator" i "Information Expert".
 */
class Faktura {
    private final String klient;
    private final List<FakturaElement> elementy;

    public Faktura(String klient) {
        this.klient = klient;
        this.elementy = new ArrayList<>();
    }

    public void dodajElement(FakturaElement element) {
        elementy.add(element);
    }

    public double obliczCalkowityKoszt() {
        double calkowityKoszt = 0.0;
        for (FakturaElement element : elementy) {
            calkowityKoszt += element.obliczKoszt();
        }
        return calkowityKoszt;
    }

    public List<FakturaElement> getElementy() {
        return elementy;
    }

    public void wyswietlFakture() {
        out.println("Faktura dla klienta: " + klient);
        for (FakturaElement element : elementy) {
            out.println(element);
        }
        out.println("Całkowity koszt: " + obliczCalkowityKoszt() + " PLN");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);

        out.print("Podaj nazwę klienta: ");
        String klient = scanner.nextLine();

        Faktura faktura = new Faktura(klient);

        while (true) {
            out.print("Podaj nazwę artykułu (lub wpisz 'koniec' aby zakończyć): ");
            String artykul = scanner.nextLine();

            if (artykul.equals("koniec")) {
                break;
            }

            out.print("Podaj ilość: ");
            int ilosc = Integer.parseInt(scanner.nextLine());

            out.print("Podaj cenę: ");
            double cena = Double.parseDouble(scanner.nextLine());

            FakturaElement element = new FakturaElement(artykul, ilosc, cena);
            faktura.dodajElement(element);
        }

        faktura.wyswietlFakture();
    }
}
