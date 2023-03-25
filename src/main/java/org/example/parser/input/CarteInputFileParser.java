package org.example.parser.input;

import org.example.model.*;
import org.example.model.enums.Mouvement;
import org.example.model.enums.Orientation;
import org.example.parser.exceptions.IncorrectFilePathException;
import org.example.parser.exceptions.InvalidInputFileFormatException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.utils.GameUtils.*;

public class CarteInputFileParser implements InputParser<Carte, String> {

    @Override
    public Carte parse(String filePath) {
        List<String> lines = getFileLines(filePath);
        return parseLines(lines);
    }

    private Carte parseLines(List<String> lines) {
        Dimensions dimensions = null;
        List<Element> elements = new ArrayList<>();
        for (String line : lines) {
            if (isComment(line)) {
                continue;
            }
            String[] lineParts = line.split(SEPARATOR);
            String lineType = lineParts[0];
            switch (lineType) {
                case CARTE -> dimensions = parseCarteDimensions(lineParts);
                case MONTAGNE -> elements.add(parseMontagne(lineParts));
                case TRESOR -> elements.add(parseTresor(lineParts));
                case AVENTURIER -> elements.add(parseAventurier(lineParts));
                default -> throw new InvalidInputFileFormatException(lineType, line);
            }
        }
        if (dimensions == null)
            throw new InvalidInputFileFormatException();
        return new Carte(dimensions, elements);
    }


    private boolean isComment(final String line) {
        return line.isEmpty() || line.startsWith(COMMENT);
    }

    private Dimensions parseCarteDimensions(String[] lineParts) {
        if(lineParts.length < 3)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        int largeur = Integer.parseInt(lineParts[1]);
        int hauteur = Integer.parseInt(lineParts[2]);
        return new Dimensions(largeur, hauteur);
    }

    private Montagne parseMontagne(String[] lineParts) {
        if(lineParts.length < 3)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        int axeHorizontal = Integer.parseInt(lineParts[1]);
        int axeVertical = Integer.parseInt(lineParts[2]);
        return new Montagne(axeHorizontal, axeVertical);
    }

    private Tresor parseTresor(String[] lineParts) {
        if(lineParts.length < 4)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        int axeHorizontal = Integer.parseInt(lineParts[1]);
        int axeVertical = Integer.parseInt(lineParts[2]);
        int nbTresors = Integer.parseInt(lineParts[3]);
        return new Tresor(axeHorizontal, axeVertical, nbTresors);
    }

    private Aventurier parseAventurier(String[] lineParts) {
        if(lineParts.length < 6)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        String nom = lineParts[1];
        int axeHorizontal = Integer.parseInt(lineParts[2]);
        int axeVertical = Integer.parseInt(lineParts[3]);
        Orientation orientation = Orientation.valueOf(lineParts[4]);
        List<Mouvement> mouvements = parseMouvements(lineParts[5]);
        return new Aventurier(axeHorizontal, axeVertical, nom, orientation, mouvements);
    }

    private List<Mouvement> parseMouvements(String mouvementsString) {
        List<Mouvement> mouvements = new ArrayList<>();
        try {
            for (char mouvement : mouvementsString.toCharArray()) {
                mouvements.add(Mouvement.valueOf(String.valueOf(mouvement)));
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidInputFileFormatException(mouvementsString);
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
