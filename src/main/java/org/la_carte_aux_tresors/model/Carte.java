package org.la_carte_aux_tresors.model;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private static Dimensions dimensions;
    private static List<Aventurier> aventuriers;
    private static List<Tresor> tresors;
    private static List<Montagne> montagnes;

    public Carte(final Dimensions dimensions, final List<Element> elements) {
        Carte.dimensions = dimensions;
        Carte.aventuriers = new ArrayList<>();
        Carte.tresors = new ArrayList<>();
        Carte.montagnes = new ArrayList<>();
        initElementLists(elements);
    }

    private void initElementLists(List<Element> elements) {
        elements.forEach(element -> {
            if (element instanceof Aventurier) {
                aventuriers.add((Aventurier) element);
            } else if (element instanceof Tresor) {
                tresors.add((Tresor) element);
            } else if (element instanceof Montagne) {
                montagnes.add((Montagne) element);
            }
        });
    }

    public static Dimensions getDimensions() {
        return dimensions;
    }

    public static List<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public static List<Tresor> getTresors() {
        return tresors;
    }

    public static List<Montagne> getMontagnes() {
        return montagnes;
    }

    private static boolean isOutOfCarte(int axeHorizontal, int axeVertical) {
        return axeHorizontal < 0 || axeHorizontal >= dimensions.getLargeur() ||
                axeVertical < 0 || axeVertical >= dimensions.getHauteur();
    }

    public static boolean isNewPositionAvailable(int newAxeHorizontal, int newAxeVertical) {
        boolean isNewPositionAvailable = true;
        if (Carte.isOutOfCarte(newAxeHorizontal, newAxeVertical)) {
            isNewPositionAvailable = false;
        } else if (isMontagneOnThisPosition(newAxeHorizontal, newAxeVertical)) {
            isNewPositionAvailable = false;
        } else if (isAventurierOnThisPosition(newAxeHorizontal, newAxeVertical)) {
            isNewPositionAvailable = false;
        }
        return isNewPositionAvailable;
    }

    private static boolean isAventurierOnThisPosition(int axeHorizontal, int axeVertical) {
        return Carte.aventuriers.stream().anyMatch(
                aventurier -> aventurier.getAxeHorizontal() == axeHorizontal && aventurier.getAxeVertical() == axeVertical);
    }

    private static boolean isMontagneOnThisPosition(int axeHorizontal, int axeVertical) {
        return Carte.montagnes.stream().anyMatch(
                montagne -> montagne.getAxeHorizontal() == axeHorizontal && montagne.getAxeVertical() == axeVertical);
    }

    public static boolean isTresorOnThisPosition(int axeHorizontal, int axeVertical) {
        return Carte.tresors.stream().anyMatch(
                tresor -> tresor.getAxeHorizontal() == axeHorizontal && tresor.getAxeVertical() == axeVertical);
    }

    public static void ramasserTresors(int axeHorizontal, int axeVertical) {
        Carte.tresors.stream()
                .filter(tresor -> tresor.getAxeHorizontal() == axeHorizontal && tresor.getAxeVertical() == axeVertical)
                .findFirst()
                .ifPresent(tresor -> {
                    tresor.ramasserTresor();
                    if (tresor.getNombreTresorsRestants() <= 0)
                        Carte.tresors.remove(tresor);
                });
    }
}
