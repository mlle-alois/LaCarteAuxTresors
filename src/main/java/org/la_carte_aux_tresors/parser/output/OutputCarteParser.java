package org.la_carte_aux_tresors.parser.output;

import org.la_carte_aux_tresors.model.Aventurier;
import org.la_carte_aux_tresors.model.Carte;
import org.la_carte_aux_tresors.model.Montagne;
import org.la_carte_aux_tresors.model.Tresor;

import java.util.List;

import static org.la_carte_aux_tresors.utils.GameUtils.*;

public class OutputCarteParser implements OutputParser<String, Carte> {

    @Override
    public String parse(Carte carte) {
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
