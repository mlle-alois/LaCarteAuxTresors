package org.example;

import org.example.application.LaCarteAuxTresorsGame;
import org.example.model.Carte;
import org.example.parser.CarteInputFileParser;

public class Main {
    public static void main(String[] args) {
        String fileName = "carte-input.txt";
        CarteInputFileParser carteInputFileParser = new CarteInputFileParser();
        Carte carte = carteInputFileParser.parse(fileName);
        LaCarteAuxTresorsGame laCarteAuxTresorsGame = new LaCarteAuxTresorsGame(carte);
        laCarteAuxTresorsGame.jouer();
    }
}
