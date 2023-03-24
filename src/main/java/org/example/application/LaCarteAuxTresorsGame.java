package org.example.application;

import org.example.generator.OutputFileGenerator;
import org.example.model.Aventurier;
import org.example.model.Carte;

public class LaCarteAuxTresorsGame {

    private final Carte carte;
    private final OutputFileGenerator outputFileGenerator;

    public LaCarteAuxTresorsGame(Carte carte) {
        this.carte = carte;
        this.outputFileGenerator = new OutputFileGenerator();
    }

    public void jouer() {
        for(Aventurier aventurier : carte.getAventuriers()) {
            aventurier.jouer();
        }
        outputFileGenerator.generate(carte.getPlanRepresentation());
    }
}
