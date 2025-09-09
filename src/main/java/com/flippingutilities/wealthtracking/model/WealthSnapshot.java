package com.flippingutilities.wealthtracking.model;

import java.time.Instant;
import java.util.Map;
import lombok.Data;

/**
 * Represents the state of the player's wealth at a single point in time.
 * This is the fundamental data point for all wealth tracking and charting.
 */
@Data
public class WealthSnapshot {
    /**
     * The time at which this snapshot was taken.
     */
    private Instant timestamp;

    /**
     * A map from the type of wealth (e.g., Bank, Inventory) to its calculated value in GP.
     */
    private Map<WTValueType, Long> wealthValues;

    //Lombok's @Data will generate constructor, getters, setters, equals, hashCode, and toString.
    //A public WealthSnapshot(Instant timestamp, Map<WTValueType, Long> wealthValues) will be available.
}
