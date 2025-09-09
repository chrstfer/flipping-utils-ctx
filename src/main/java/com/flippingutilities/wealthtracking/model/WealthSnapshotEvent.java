package com.flippingutilities.wealthtracking.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * An event that is fired when a new wealth snapshot is taken.
 * The UI components can subscribe to this event to know when to update.
 */
@Data
@AllArgsConstructor
public class WealthSnapshotEvent {
    private final WealthSnapshot newSnapshot;
}
