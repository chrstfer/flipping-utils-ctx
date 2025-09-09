package com.flippingutilities.wealthtracking;

import com.flippingutilities.wealthtracking.model.AccountWealth;
import com.flippingutilities.wealthtracking.model.WTValueType;
import com.flippingutilities.wealthtracking.model.WealthSnapshot;
import com.flippingutilities.wealthtracking.model.WealthSnapshotEvent;
import com.flippingutilities.wealthtracking.util.ContainerScanner;
import com.flippingutilities.wealthtracking.util.ItemPricer;
import java.time.Instant;
import java.util.EnumMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;

/**
 * The central nervous system of the wealth tracking feature. It listens to game events,
 * orchestrates the scanning of item containers, creates wealth snapshots, and holds the
 * main AccountWealth object. It is the single source of truth for the UI.
 */
@Slf4j
@Singleton
public class WealthTracker {
    private final ItemManager itemManager;
    private final Client client;
    private final EventBus eventBus;

    @Getter
    private AccountWealth accountWealth;
    private ContainerScanner containerScanner;

    @Inject
    private WealthTracker(Client client, ItemManager itemManager, EventBus eventBus) {
        this.client = client;
        this.itemManager = itemManager;
        this.eventBus = eventBus;
        init();
    }

    private void init() {
        ItemPricer itemPricer = new ItemPricer(itemManager);
        this.containerScanner = new ContainerScanner(itemPricer);
        this.accountWealth = new AccountWealth();
        log.info("Wealth Tracker initialized");
    }

    @Subscribe
    public void on(ItemContainerChanged event) {
        if (event.getContainerId() == InventoryID.BANK.getId() || event.getContainerId() == InventoryID.INVENTORY.getId()) {
            log.debug("Tracked container changed: {}. Taking snapshot.", event.getContainerId());
            takeSnapshot("Container changed");
        }
    }

    public void takeSnapshot(String reason) {
        log.info("Taking wealth snapshot, reason: {}", reason);

        ItemContainer bank = client.getItemContainer(InventoryID.BANK);
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

        long bankValue = containerScanner.calculateContainerValue(bank);
        long inventoryValue = containerScanner.calculateContainerValue(inventory);
        long overallValue = bankValue + inventoryValue;

        Map<WTValueType, Long> values = new EnumMap<>(WTValueType.class);
        values.put(WTValueType.BANK, bankValue);
        values.put(WTValueType.INVENTORY, inventoryValue);
        values.put(WTValueType.OVERALL, overallValue);

        WealthSnapshot snapshot = new WealthSnapshot();
        snapshot.setTimestamp(Instant.now());
        snapshot.setWealthValues(values);

        accountWealth.addSnapshot(snapshot);

        eventBus.post(new WealthSnapshotEvent(snapshot));
    }
}
