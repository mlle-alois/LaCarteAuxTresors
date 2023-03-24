package org.example.parser;

import org.example.model.*;
import org.example.model.enums.CaseType;
import org.example.model.enums.Mouvement;
import org.example.model.enums.Orientation;
import org.example.parser.exceptions.IncorrectFilePathException;
import org.example.parser.exceptions.InvalidFileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarteInputFileParser implements FileParser<Carte> {
    private static final String SEPARATOR = " - ";

    @Override
    public Carte parse(String filePath) {
        List<String> lines = getFileLines(filePath);
        return parseCarte(lines);
    }

    private Carte parseCarte(List<String> lines) {
        Dimensions dimensions = null;
        List<Element> elements = new ArrayList<>();
        for (String line : lines) {
            String[] lineParts = line.split(SEPARATOR);
            String lineType = lineParts[0];
            switch (lineType) {
                case "C" -> dimensions = parseCarteDimensions(lineParts);
                case "M" -> elements.add(parseMontagne(lineParts));
                case "T" -> elements.add(parseTresor(lineParts));
                case "A" -> elements.add(parseAventurier(lineParts));
                default -> throw new InvalidFileFormatException(lineType, line);
            }
        }
        if(dimensions == null)
            throw new InvalidFileFormatException();
        return new Carte(dimensions, elements);
    }

    private Dimensions parseCarteDimensions(String[] lineParts) {
        //TODO verifier si ça plante quand on a pas 3 parties
        int largeur = Integer.parseInt(lineParts[1]);
        int hauteur = Integer.parseInt(lineParts[2]);
        return new Dimensions(largeur, hauteur);
    }

    private Montagne parseMontagne(String[] lineParts) {
        //TODO verifier si ça plante quand on a pas 3 parties
        int axeHorizontal = Integer.parseInt(lineParts[1]);
        int axeVertical = Integer.parseInt(lineParts[2]);
        return new Montagne(axeHorizontal, axeVertical, CaseType.MONTAGNE);
    }

    private Tresor parseTresor(String[] lineParts) {
        //TODO verifier si ça plante quand on a pas 4 parties
        int axeHorizontal = Integer.parseInt(lineParts[1]);
        int axeVertical = Integer.parseInt(lineParts[2]);
        int nbTresors = Integer.parseInt(lineParts[3]);
        return new Tresor(axeHorizontal, axeVertical, CaseType.TRESOR, nbTresors);
    }

    private Aventurier parseAventurier(String[] lineParts) {
        //TODO verifier si ça plante quand on a pas 6 parties
        String nom = lineParts[1];
        int axeHorizontal = Integer.parseInt(lineParts[2]);
        int axeVertical = Integer.parseInt(lineParts[3]);
        Orientation orientation = Orientation.valueOf(lineParts[4]);
        List<Mouvement> mouvements = parseMouvements(lineParts[5]);
        return new Aventurier(axeHorizontal, axeVertical, CaseType.AVENTURIER, nom, orientation, mouvements);
    }

    private List<Mouvement> parseMouvements(String mouvementsString) {
        List<Mouvement> mouvements = new ArrayList<>();
        for (char c : mouvementsString.toCharArray()) {
            mouvements.add(Mouvement.valueOf(String.valueOf(c)));
        }
        return mouvements;
    }

    private List<String> getFileLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            fileReader.close();
            return lines;
        } catch (FileNotFoundException e) {
            throw new IncorrectFilePathException(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
