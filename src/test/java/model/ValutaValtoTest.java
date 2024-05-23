package model;

import controller.NegativeNumberException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValutaValtoTest {

    private ValutaValto model;

    @BeforeEach
    public void setUp() {
        model = new ValutaValto();
        // előzménytörlés a teszt előtt
        File file = new File("atvaltas.xml");
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    public void tearDown() {
        // előzmény törlés a teszt után
        File file = new File("atvaltas.xml");
        if (file.exists()) {
            file.delete();
        }
    }

    //
    @Test
    public void Teszt_PozitivSzam() throws NegativeNumberException {
        double result = model.atvaltas(100, "USA dollár", "Euró");
        assertEquals(83.0, result, 0.01);
    }

    @Test
    public void TesztMentes() {
        model.mentes(100, "USA dollár", "Euró", 83);
        List<String> elozmeny = model.olvasas();
        assertFalse(elozmeny.isEmpty());
        assertEquals("100,00 USA dollár -> 83,00 Euró", elozmeny.get(0).trim());
    }

    @Test
    public void TesztOlvasas_Ures() {
        List<String> elozmeny = model.olvasas();
        assertTrue(elozmeny.isEmpty());
    }

    @Test
    public void TesztOlvasas_NemUres() {
        model.mentes(100, "USA dollár", "Euró", 83);
        List<String> elozmeny = model.olvasas();
        assertFalse(elozmeny.isEmpty());
        assertEquals("100,00 USA dollár -> 83,00 Euró", elozmeny.get(0).trim());
    }

    @Test
    public void TesztElozmenyTorles() {
        model.mentes(100, "USA dollár", "Euró", 83);
        model.elozmenyTorles();
        List<String> elozmeny = model.olvasas();
        assertTrue(elozmeny.isEmpty());
    }
}
