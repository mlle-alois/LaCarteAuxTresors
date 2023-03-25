package generator;

import org.la_carte_aux_tresors.generator.FileGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileGeneratorTest {

    FileGenerator fileGenerator;

    @Before
    public void setUp() {
        fileGenerator = new FileGenerator();
    }

    @Test
    public void shouldGenerateFile() throws IOException {
        String path = "src/test/resources/test-output.txt";
        fileGenerator.generate(path, "Hello World!");
        assertTrue(new File(path).exists());
        List<String> lines = Files.readAllLines(Path.of(path));
        assertEquals("Hello World!", lines.get(0));
    }
}
