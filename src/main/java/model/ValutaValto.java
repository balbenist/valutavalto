package model;

import controller.NegativeNumberException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValutaValto {

    private static final String FILE_PATH = "atvaltas.xml";

    public double atvaltas(double osszeg, String forras, String cel) throws NegativeNumberException {
        if (osszeg < 0) {
            throw new NegativeNumberException("Nem adhatsz meg negatív számot!");
        }

        double szorzo = 1.0;
        if (forras.equals("USA dollár") && cel.equals("Euró")) {
            szorzo = 0.83;
        } else if (forras.equals("Euró") && cel.equals("USA dollár")) {
            szorzo = 1.16;
        } else if (forras.equals("USA dollár") && cel.equals("Forint")) {
            szorzo = 298.4;
        } else if (forras.equals("Forint") && cel.equals("USA dollár")) {
            szorzo = 0.00316;
        } else if (forras.equals("Euró") && cel.equals("Forint")) {
            szorzo = 378.4;
        } else if (forras.equals("Forint") && cel.equals("Euró")) {
            szorzo = 0.684;
        }
        return osszeg * szorzo;
    }

    public void mentes(double osszeg, String forras, String cel, double eredmeny) {
        try (BufferedWriter ment = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            ment.write(String.format("%.2f %s -> %.2f %s%n", osszeg, forras, eredmeny, cel));
        } catch (IOException e) {
            System.out.println("Nem található a fájl!");
        }
    }

    public List<String> olvasas() {
        List<String> lista = new ArrayList<>();
        try (Scanner olvas = new Scanner(new FileReader(FILE_PATH))) {
            while (olvas.hasNextLine()) {
                lista.add(olvas.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void elozmenyTorles() {
        File xmlFile = new File(FILE_PATH);
        if (xmlFile.exists()) {
            boolean deleted = xmlFile.delete();
            if (deleted) {
                System.out.println("A fájl sikeresen törölve: " + FILE_PATH);
            } else {
                System.out.println("Nem sikerült törölni a fájlt: " + FILE_PATH);
            }
        } else {
            System.out.println("A fájl nem található: " + FILE_PATH);
        }
    }
}

