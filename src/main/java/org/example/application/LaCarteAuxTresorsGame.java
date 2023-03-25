package org.example.application;

import org.example.generator.FileGenerator;
import org.example.parser.output.OutputCarteParser;
import org.example.model.Aventurier;
import org.example.model.Carte;
import org.example.parser.input.InputFileParser;

import static org.example.utils.GameUtils.INPUT_FILE_PATH;
import static org.example.utils.GameUtils.OUTPUT_FILE_NAME;

public class LaCarteAuxTresorsGame {
    private final InputFileParser inputFileParser;
    private final OutputCarteParser outputCarteParser;
    private final FileGenerator fileGenerator;

    public LaCarteAuxTresorsGame() {
        this.inputFileParser = new InputFileParser();
        this.outputCarteParser = new OutputCarteParser();
        this.fileGenerator = new FileGenerator();
    }

    public void jouer() {
        Carte carte = inputFileParser.parse(INPUT_FILE_PATH);
        boolean everyAventuriersHasFinished;
        do {
            everyAventuriersHasFinished = true;
            for (Aventurier aventurier : Carte.getAventuriers()) {
                boolean hasPlayed = aventurier.jouer();
                if (hasPlayed) {
                    everyAventuriersHasFinished = false;
                }
            }
        } while (!everyAventuriersHasFinished);
        String content = outputCarteParser.parse(carte);
        fileGenerator.generate(OUTPUT_FILE_NAME, content);
    }
}
