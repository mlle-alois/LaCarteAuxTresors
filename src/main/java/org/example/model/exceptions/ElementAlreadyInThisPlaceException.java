package org.example.model.exceptions;

import org.example.model.enums.CaseType;

public class ElementAlreadyInThisPlaceException extends RuntimeException {
    public ElementAlreadyInThisPlaceException(final CaseType elementToPlaceType, final int axeHorizontal,
                                              final int axeVertical, final CaseType existingElementType) {
        super(String.format("Element %s could not be placed on axe(%d,%d) because there is already a %s",
                elementToPlaceType, axeHorizontal, axeVertical, existingElementType));
    }
}
