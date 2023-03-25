package parser.input;

import org.la_carte_aux_tresors.model.Carte;
import org.la_carte_aux_tresors.parser.input.InputFileParser;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class InputFileParserTest {

    InputFileParser inputFileParser;
    String filePath = "src/test/resources/test-carte-input.txt";

    @Before
    public void setUp() {
        inputFileParser = new InputFileParser();
    }

    @Test
    public void shouldParseInputFile() {
        inputFileParser.parse(filePath);
        assertEquals(3, Carte.getDimensions().getLargeur());
        assertEquals(4, Carte.getDimensions().getHauteur());
        assertEquals(2, Carte.getMontagnes().size());
        assertEquals(2, Carte.getTresors().size());
        assertEquals(1, Carte.getAventuriers().size());
    }

    @Test
    public void shouldParseInputFileWithEmptyLines() {
        filePath = "src/test/resources/test-carte-input-with-empty-lines.txt";
        inputFileParser.parse(filePath);
        assertEquals(3, Carte.getDimensions().getLargeur());
        assertEquals(4, Carte.getDimensions().getHauteur());
        assertEquals(2, Carte.getMontagnes().size());
        assertEquals(2, Carte.getTresors().size());
        assertEquals(1, Carte.getAventuriers().size());
    }

    @Test
    public void shouldThrowExceptionWhenFileNotFound() throws RuntimeException {
        filePath = "src/test/resources/test-carte-input-not-found.txt";
        assertThrows(RuntimeException.class, () -> {
            inputFileParser.parse(filePath);
        });
    }

    @Test
    public void shouldThrowExceptionWhenFileIsEmpty() throws RuntimeException {
        filePath = "src/test/resources/test-carte-input-empty.txt";
        assertThrows(RuntimeException.class, () -> {
            inputFileParser.parse(filePath);
        });
    }

    @Test
    public void shouldThrowExceptionWhenFileIsNotValidInTheNumberOfParametersForAventurier() throws RuntimeException {
        filePath = "src/test/resources/test-carte-input-not-valid.txt";
        assertThrows(RuntimeException.class, () -> {
            inputFileParser.parse(filePath);
        });
    }

    @Test
    public void shouldThrowExceptionWhenFileIsNotValidInTheTypeOfLine() throws RuntimeException {
        filePath = "src/test/resources/test-carte-input-not-valid2.txt";
        assertThrows(RuntimeException.class, () -> {
            inputFileParser.parse(filePath);
        });
    }
}
