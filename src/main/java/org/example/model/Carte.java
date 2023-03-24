package org.example.model;

import org.example.model.enums.CaseType;
import org.example.model.exceptions.ElementAlreadyInThisPlaceException;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private final Dimensions dimensions;
    private CaseType[][] plan;
    private final List<Element> elements;
    private final List<Aventurier> aventuriers;
    private final List<Tresor> tresors;

    private final List<Montagne> obstacles;

    public Carte(final Dimensions dimensions, final List<Element> elements) {
        this.dimensions = dimensions;
        this.elements = elements;
        this.aventuriers = new ArrayList<>();
        this.tresors = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        initPlan();
        initElementLists();
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
        placerElements();
    }

    private void initElementLists() {
        elements.forEach(element -> {
            if (element instanceof Aventurier) {
                aventuriers.add((Aventurier) element);
            } else if (element instanceof Tresor) {
                tresors.add((Tresor) element);
            } else if (element instanceof Montagne) {
                obstacles.add((Montagne) element);
            }
        });
    }

    private void placerElements() {
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

    public Dimensions getDimensions() {
        return dimensions;
    }

    public List<Aventurier> getAventuriers() {
        return aventuriers;
    }

    public List<Tresor> getTresors() {
        return tresors;
    }

    public List<Montagne> getObstacles() {
        return obstacles;
    }

    public String getPlanRepresentation() {
        StringBuilder plan = new StringBuilder();
        for (int indexY = 0; indexY < dimensions.getHauteur(); indexY++) {
            for (int indexX = 0; indexX < dimensions.getLargeur(); indexX++) {
                plan.append("   ")
                        .append(this.plan[indexX][indexY].getSymbol())
                        .append("   ");
            }
            plan.append("\n");
        }
        return plan.toString();
    }
}
