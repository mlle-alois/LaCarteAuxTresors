package org.example.parser.input;

import org.example.model.*;
import org.example.model.enums.Orientation;
import org.example.parser.exceptions.IncorrectFilePathException;
import org.example.parser.exceptions.InvalidInputFileFormatException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.utils.GameUtils.*;

public class InputFileParser implements InputParser<Carte, String> {

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
                case MONTAGNE -> elements.add(parseMontagneLine(lineParts));
                case TRESOR -> elements.add(parseTresorLine(lineParts));
                case AVENTURIER -> elements.add(parseAventurierLine(lineParts));
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

    private Montagne parseMontagneLine(String[] lineParts) {
        if(lineParts.length < 3)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        int axeHorizontal = Integer.parseInt(lineParts[1]);
        int axeVertical = Integer.parseInt(lineParts[2]);
        return new Montagne(axeHorizontal, axeVertical);
    }

    private Tresor parseTresorLine(String[] lineParts) {
        if(lineParts.length < 4)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        int axeHorizontal = Integer.parseInt(lineParts[1]);
        int axeVertical = Integer.parseInt(lineParts[2]);
        int nbTresors = Integer.parseInt(lineParts[3]);
        return new Tresor(axeHorizontal, axeVertical, nbTresors);
    }

    private Aventurier parseAventurierLine(String[] lineParts) {
        if(lineParts.length < 6)
            throw new InvalidInputFileFormatException(lineParts.length, Arrays.toString(lineParts));
        String nom = lineParts[1];
        int axeHorizontal = Integer.parseInt(lineParts[2]);
        int axeVertical = Integer.parseInt(lineParts[3]);
        Orientation orientation = Orientation.valueOf(lineParts[4]);
        return new Aventurier(axeHorizontal, axeVertical, nom, orientation, lineParts[5]);
    }

    private List<String> getFileLines(String filePath) {
        try {
            return Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
