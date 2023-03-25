package parser.output;

import org.la_carte_aux_tresors.model.*;
import org.la_carte_aux_tresors.model.enums.Orientation;
import org.la_carte_aux_tresors.parser.output.OutputCarteParser;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OutputCarteParserTest {

    OutputCarteParser outputCarteParser;
    Carte carte;

    @Before
    public void setUp() {
        outputCarteParser = new OutputCarteParser();
        List<Element> elements = new ArrayList<>();
        elements.add(new Aventurier(1, 2, "Lara", Orientation.S, "ADADAGGA"));
        elements.add(new Montagne(1, 2));
        elements.add(new Tresor(1, 3, 2));
        carte = new Carte(new Dimensions(3, 4), elements);
    }

    @Test
    public void shouldParseOutputFile() {
        String result = outputCarteParser.parse(carte);
        assertEquals(result, """
                C - 4 - 3
                M - 1 - 2
                # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}
                T - 1 - 3 - 2
                # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}
                A - Lara - 1 - 2 - S - 0
                """);
    }
}
