package org.example.parser.output;

import org.example.model.Aventurier;
import org.example.model.Carte;
import org.example.model.Montagne;
import org.example.model.Tresor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.example.utils.GameUtils.*;

public class OutputFileParser implements OutputParser<File> {

    @Override
    public File parse() {
        try {
            FileWriter writer = new FileWriter(OUTPUT_FILE_NAME);
            String content = parseCarte();
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new File(OUTPUT_FILE_NAME);
    }

    private String parseCarte() {
        return CARTE + SEPARATOR + Carte.getDimensions().getHauteur() +
                SEPARATOR + Carte.getDimensions().getLargeur() + "\n" +
                parseMontagnes(Carte.getMontagnes()) +
                TRESOR_OUTPUT_FORMAT + "\n" +
                parseTresors(Carte.getTresors()) +
                AVENTURIER_OUTPUT_FORMAT + "\n" +
                parseAventuriers(Carte.getAventuriers());
    }

    private String parseMontagnes(List<Montagne> montagnes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Montagne montagne : montagnes) {
            stringBuilder.append(MONTAGNE)
                    .append(SEPARATOR).append(montagne.getAxeHorizontal())
                    .append(SEPARATOR).append(montagne.getAxeVertical()).append("\n");
        }
        return stringBuilder.toString();
    }

    private String parseTresors(List<Tresor> tresors) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Tresor tresor : tresors) {
            stringBuilder.append(TRESOR)
                    .append(SEPARATOR).append(tresor.getAxeHorizontal())
                    .append(SEPARATOR).append(tresor.getAxeVertical())
                    .append(SEPARATOR).append(tresor.getNombreTresorsRestants()).append("\n");
        }
        return stringBuilder.toString();
    }

    private String parseAventuriers(List<Aventurier> aventuriers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Aventurier aventurier : aventuriers) {
            stringBuilder.append(AVENTURIER)
                    .append(SEPARATOR).append(aventurier.getNom())
                    .append(SEPARATOR).append(aventurier.getAxeHorizontal())
                    .append(SEPARATOR).append(aventurier.getAxeVertical())
                    .append(SEPARATOR).append(aventurier.getOrientation())
                    .append(SEPARATOR).append(aventurier.getNbTresorRamasse()).append("\n");
        }
        return stringBuilder.toString();
    }
}
