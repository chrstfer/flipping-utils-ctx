package com.flippingutilities.wealthtracking.util;

import net.runelite.client.game.ItemManager;

/**
 * A utility class responsible for providing the price of an item.
 * This abstracts the logic of how the price is determined (e.g., GE price, HA price, etc.)
 * from the components that need the price.
 */
public class ItemPricer {
    private final ItemManager itemManager;

    /**
     * Constructor for the ItemPricer.
     *
     * @param itemManager RuneLite's item manager, used to get item information and prices.
     */
    public ItemPricer(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    /**
     * Gets the price for a given item ID.
     * For now, this will just use the standard GE price provided by the ItemManager.
     * In the future, this could be expanded to support other pricing strategies
     * (e.g., high alchemy value, user-defined prices).
     *
     * @param itemId the ID of the item to price.
     * @return the price of the item in GP.
     */
    public long getPrice(int itemId) {
        // itemManager.getItemPrice returns the GE price.
        // It's a good default. We can add more complex logic later if needed.
        return itemManager.getItemPrice(itemId);
    }
}
