package com.flippingutilities.wealthtracking.model;

import java.util.List;
import java.util.ArrayList;
import lombok.Data;

/**
 * Represents the complete wealth history for the player's account.
 * This class is the primary data model that gets serialized for persistence.
 * It holds a time-ordered list of all wealth snapshots.
 */
@Data
public class AccountWealth {
    /**
     * A time-ordered list of wealth snapshots. The latest snapshot is at the end of the list.
     */
    private List<WealthSnapshot> history = new ArrayList<>();

    //Lombok's @Data will generate constructor, getters, setters, etc.

    /**
     * Adds a new snapshot to the history.
     * Implementations should ensure the list remains sorted by timestamp if necessary.
     *
     * @param snapshot the new snapshot to add.
     */
    public void addSnapshot(WealthSnapshot snapshot) {
        //In a more complex system, we might validate the timestamp ordering here.
        //For now, we just add it.
        history.add(snapshot);
    }

    /**
     * Retrieves the most recent wealth snapshot.
     *
     * @return the latest WealthSnapshot, or null if the history is empty.
     */
    public WealthSnapshot getLatestSnapshot() {
        if (history.isEmpty()) {
            return null;
        }
        return history.get(history.size() - 1);
    }
}
