package org.example.application;

import org.example.parser.output.OutputFileParser;
import org.example.model.Aventurier;
import org.example.model.Carte;
import org.example.parser.input.InputFileParser;

import static org.example.utils.GameUtils.INPUT_FILE_PATH;

public class LaCarteAuxTresorsGame {
    private final InputFileParser inputFileParser;
    private final OutputFileParser outputFileParser;

    public LaCarteAuxTresorsGame() {
        this.inputFileParser = new InputFileParser();
        this.outputFileParser = new OutputFileParser();
    }

    public void jouer() {
        inputFileParser.parse(INPUT_FILE_PATH);
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
        outputFileParser.parse();
    }
}
