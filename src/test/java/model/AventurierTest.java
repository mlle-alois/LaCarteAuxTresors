package model;

import org.example.model.*;
import org.example.model.enums.Orientation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AventurierTest {

    private Aventurier aventurier1;
    private Aventurier aventurier2;

    @Before
    public void setUp() {
        List<Element> elements = new ArrayList<>();
        aventurier1 = new Aventurier(1, 2, "Lara", Orientation.S, "ADADAGGA");
        aventurier2 = new Aventurier(0, 1, "Thimeo", Orientation.S, "");
        elements.add(aventurier1);
        elements.add(aventurier2);
        elements.add(new Montagne(1, 2));
        elements.add(new Tresor(1, 3, 1));
        new Carte(new Dimensions(3, 4), elements);
    }

    @Test
    public void aventurierShouldPlay() {
        boolean hasPlayed = aventurier1.jouer();
        assertTrue(hasPlayed);
    }

    @Test
    public void aventurierShouldNotPlay() {
        boolean hasPlayed = aventurier2.jouer();
        assertFalse(hasPlayed);
    }

    @Test
    public void aventurierShouldMove() {
        aventurier1.jouer();
        assertEquals(1, aventurier1.getAxeHorizontal());
        assertEquals(3, aventurier1.getAxeVertical());
    }

    @Test
    public void aventurierShouldNotMoveBecauseOfMontagne() {
        aventurier1.setAxeHorizontal(1);
        aventurier1.setAxeVertical(1);
        aventurier1.jouer();
        assertEquals(1, aventurier1.getAxeHorizontal());
        assertEquals(1, aventurier1.getAxeVertical());
    }

    @Test
    public void aventurierShouldNotMoveBecauseOfAnotherAventurier() {
        aventurier1.setAxeHorizontal(0);
        aventurier1.setAxeVertical(0);
        aventurier1.jouer();
        assertEquals(0, aventurier1.getAxeHorizontal());
        assertEquals(0, aventurier1.getAxeVertical());
    }

    @Test
    public void aventurierShouldNotMoveBecauseOfOutOfCarte() {
        aventurier1.setAxeHorizontal(2);
        aventurier1.setAxeVertical(3);
        aventurier1.jouer();
        assertEquals(2, aventurier1.getAxeHorizontal());
        assertEquals(3, aventurier1.getAxeVertical());
    }

    @Test
    public void aventurierShouldCollectTresor() {
        aventurier1.setAxeHorizontal(1);
        aventurier1.setAxeVertical(2);
        aventurier1.jouer();
        assertEquals(1, aventurier1.getNbTresorRamasse());
    }

}
