package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.ValutaValto;


import java.util.List;


public class ValutaValtoController {
    @FXML
    private TextField Osszeg;

    @FXML
    private ComboBox<String> Forras;

    @FXML
    private ComboBox<String> Cel;

    @FXML
    private Label Eredmeny;

    @FXML
    private TextArea Elozmeny;

    private ValutaValto model = new ValutaValto();

    @FXML
    private void AtvaltoGomb() {
        try {
            double osszeg = Double.parseDouble(Osszeg.getText());
            String forras = Forras.getValue();
            String cel = Cel.getValue();
            double eredmeny = model.atvaltas(osszeg, forras, cel);
            Eredmeny.setText(String.format("Eredmény: %.2f %s", eredmeny, cel));
            model.mentes(osszeg, forras, cel, eredmeny);
            Olvasas();
        } catch (NumberFormatException ex) {
            System.out.println("Csak számot írhat be!");
        } catch (NegativeNumberException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void Olvasas() {
        try {
            List<String> lista = model.olvasas();
            Elozmeny.setText(String.join("\n", lista));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ElozmenyTorles() {
        model.elozmenyTorles();
        Elozmeny.clear();
    }
}

