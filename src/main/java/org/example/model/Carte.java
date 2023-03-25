package org.example.model;

import org.example.model.enums.CaseType;
import org.example.model.exceptions.ElementAlreadyInThisPlaceException;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private static Dimensions dimensions;
    private static CaseType[][] plan;
    private static List<Aventurier> aventuriers;
    private static List<Tresor> tresors;
    private static List<Montagne> montagnes;

    public Carte(final Dimensions dimensions, final List<Element> elements) {
        Carte.dimensions = dimensions;
        Carte.aventuriers = new ArrayList<>();
        Carte.tresors = new ArrayList<>();
        Carte.montagnes = new ArrayList<>();
        initPlan();
        initElementLists(elements);
        placerElements(elements);
    }

    private void initPlan() {
        int carteLargeur = dimensions.getLargeur();
        int carteHauteur = dimensions.getHauteur();
        plan = new CaseType[carteLargeur][carteHauteur];
        for (int indexX = 0; indexX < carteLargeur; indexX++) {
            for (int indexY = 0; indexY < carteHauteur; indexY++) {
                plan[indexX][indexY] = CaseType.PLAINE;
            }
        }
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

    private void placerElements(List<Element> elements) {
        elements.forEach(element -> {
            int axeHorizontal = element.getAxeHorizontal();
            int axeVertical = element.getAxeVertical();
            CaseType existingElementType = plan[axeHorizontal][axeVertical];
            CaseType elementToPlaceType = element.getElementType();
            if (existingElementType == CaseType.PLAINE) {
                plan[axeHorizontal][axeVertical] = elementToPlaceType;
            } else {
                throw new ElementAlreadyInThisPlaceException(
                        elementToPlaceType,
                        element.getAxeHorizontal(),
                        element.getAxeVertical(),
                        existingElementType);
            }
        });
    }

    public static Dimensions getDimensions() {
        return dimensions;
    }

    public List<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public List<Montagne> getMontagnes() {
        return montagnes;
    }

    public static String getPlanRepresentation() {
        StringBuilder plan = new StringBuilder();
        for (int indexY = 0; indexY < dimensions.getHauteur(); indexY++) {
            for (int indexX = 0; indexX < dimensions.getLargeur(); indexX++) {
                plan.append("   ")
                        .append(Carte.plan[indexX][indexY].getSymbol())
                        .append("   ");
            }
            plan.append("\n");
        }
        return plan.toString();
    }

    private static boolean isOutOfCarte(int axeHorizontal, int axeVertical) {
        return axeHorizontal < 0 || axeHorizontal >= dimensions.getLargeur() || axeVertical < 0 || axeVertical >= dimensions.getHauteur();
    }


    public static boolean isNewPositionAvailable(int axeHorizontal, int axeVertical) {
        boolean isNewPositionAvailable = true;

        if (Carte.isOutOfCarte(axeHorizontal, axeVertical)) {
            isNewPositionAvailable = false;
        } else if (isMontagneOnThisPosition(axeHorizontal, axeVertical)) {
            isNewPositionAvailable = false;
        } else if (isAventurierOnThisPosition(axeHorizontal, axeVertical)) {
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

    private static boolean isTresorOnThisPosition(int axeHorizontal, int axeVertical) {
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

    public static void moveAventurier(int oldAxeHorizontal, int oldAxeVertical, int newAxeHorizontal, int newAxeVertical, Aventurier aventurier) {
        if (Carte.isNewPositionAvailable(newAxeHorizontal, newAxeVertical)) {
            if (Carte.plan[newAxeHorizontal][newAxeVertical] == CaseType.TRESOR) {
                aventurier.ramasserTresor(newAxeHorizontal, newAxeVertical);
            }
            if (isTresorOnThisPosition(oldAxeHorizontal, oldAxeVertical)) {
                Carte.plan[oldAxeHorizontal][oldAxeVertical] = CaseType.TRESOR;
            } else {
                Carte.plan[oldAxeHorizontal][oldAxeVertical] = CaseType.PLAINE;
            }
            Carte.plan[newAxeHorizontal][newAxeVertical] = CaseType.AVENTURIER;
        }
    }
}
