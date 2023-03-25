package model;

import org.example.model.*;
import org.example.model.enums.Orientation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarteTest {

    @Before
    public void setUp() {
        List<Element> elements = new ArrayList<>();
        elements.add(new Aventurier(1, 2, "Lara", Orientation.S, "ADADAGGA"));
        elements.add(new Montagne(1, 2));
        elements.add(new Tresor(1, 3, 2));
        new Carte(new Dimensions(3, 4), elements);
    }

    @Test
    public void newPositionIsAvailable() {
        assertTrue(Carte.isNewPositionAvailable(1, 1));
    }

    @Test
    public void newPositionIsNotAvailable() {
        assertFalse(Carte.isNewPositionAvailable(1, 2));
    }

    @Test
    public void tresorIsOnThisPosition() {
        assertTrue(Carte.isTresorOnThisPosition(1, 3));
    }

    @Test
    public void tresorIsNotOnThisPosition() {
        assertFalse(Carte.isTresorOnThisPosition(1, 2));
    }

    @Test
    public void tresorShouldHasBeenCollected() {
        assertEquals(2, Carte.getTresors().get(0).getNombreTresorsRestants());
        Carte.ramasserTresors(1, 3);
        assertEquals(1, Carte.getTresors().get(0).getNombreTresorsRestants());
    }
}
