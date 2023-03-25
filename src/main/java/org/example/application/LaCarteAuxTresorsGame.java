package org.example.application;

import org.example.parser.output.CarteOutputFileParser;
import org.example.model.Aventurier;
import org.example.model.Carte;
import org.example.parser.input.CarteInputFileParser;

import static org.example.utils.GameUtils.INPUT_FILE_PATH;

public class LaCarteAuxTresorsGame {
    private final CarteInputFileParser carteInputFileParser;
    private final CarteOutputFileParser outputFileGenerator;

    public LaCarteAuxTresorsGame() {
        this.carteInputFileParser = new CarteInputFileParser();
        this.outputFileGenerator = new CarteOutputFileParser();
    }

    public void jouer() {
        Carte carte = carteInputFileParser.parse(INPUT_FILE_PATH);
        boolean everyAventuriersHasFinished;
        do {
            everyAventuriersHasFinished = true;
            for (Aventurier aventurier : carte.getAventuriers()) {
                boolean hasPlayed = aventurier.jouer();
                if (hasPlayed) {
                    everyAventuriersHasFinished = false;
                }
            }
        } while (!everyAventuriersHasFinished);
        outputFileGenerator.parse(carte);
    }
}
