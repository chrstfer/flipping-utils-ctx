package com.flippingutilities.wealthtracking.util;

import net.runelite.api.Item;
import net.runelite.api.ItemContainer;

/**
 * A utility to calculate the total value of a given ItemContainer.
 * It uses an ItemPricer to value each item in the container.
 */
public class ContainerScanner {
    private final ItemPricer itemPricer;

    /**
     * Constructor for the ContainerScanner.
     *
     * @param itemPricer The pricing utility to use for valuing items.
     */
    public ContainerScanner(ItemPricer itemPricer) {
        this.itemPricer = itemPricer;
    }

    /**
     * Calculates the total value of all items in the given container.
     * It iterates through the items, gets their price from the ItemPricer,
     * and sums them up.
     *
     * @param container The ItemContainer to scan (e.g., bank, inventory).
     * @return The total value of the container in GP. Returns 0 if the container is null.
     */
    public long calculateContainerValue(ItemContainer container) {
        if (container == null) {
            return 0L;
        }

        long totalValue = 0L;
        for (Item item : container.getItems()) {
            if (item == null || item.getId() == -1) {
                continue;
            }
            //Note: item.getQuantity() is important! An item stack is a single Item object.
            long itemPrice = itemPricer.getPrice(item.getId());
            totalValue += itemPrice * item.getQuantity();
        }
        return totalValue;
    }
}
