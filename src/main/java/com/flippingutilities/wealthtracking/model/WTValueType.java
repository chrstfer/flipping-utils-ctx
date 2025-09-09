package com.flippingutilities.wealthtracking.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents a category of wealth that can be tracked. This enum drives what is displayed
 * in the UI and what is stored in each WealthSnapshot.
 */
@AllArgsConstructor
@Getter
public enum WTValueType {
    INVENTORY("Inventory"),
    BANK("Bank"),
    OVERALL("Overall"); // This could be a calculated value based on others.

    private final String name;
}
