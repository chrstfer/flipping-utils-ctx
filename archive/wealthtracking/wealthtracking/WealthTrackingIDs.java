package com.flippingutilities.wealthtracking;

import net.runelite.api.annotations.Varbit;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.api.gameval.VarbitID;

// deprecated
import net.runelite.api.Varbits;


public class WealthTrackingIDs
{
	// the IDs for inventory containers we need to keep track of; will be split off
	static final int COIN_ITEM_ID = ItemID.COINS; // 995
	static final int RUNE_POUCH_ITEM_ID = ItemID.BH_RUNE_POUCH; // 12791;
	static final int DIVINE_RUNE_POUCH_ITEM_ID = ItemID.DIVINE_RUNE_POUCH; // 27281
	static final int HERB_POUCH_ITEM_ID = 24478; // todo switch to using ItemID (later, that file slows IDEA down)

	// SEED_BOX_OPEN_ITEM_ID =

	// from ClueScrollPlugin
	// todo switch to new varbits api
	private static final int[] RUNEPOUCH_AMOUNT_VARBITS = {

		VarbitID.RUNE_POUCH_QUANTITY_1,
		VarbitID.RUNE_POUCH_QUANTITY_2,
		VarbitID.RUNE_POUCH_QUANTITY_3,
		VarbitID.RUNE_POUCH_QUANTITY_4
	};
	private static final int[] RUNEPOUCH_RUNE_VARBITS = {
		// 29, 1622, 1623, 14285
		VarbitID.RUNE_POUCH_TYPE_1,
		VarbitID.RUNE_POUCH_TYPE_2,
		VarbitID.RUNE_POUCH_TYPE_3,
		VarbitID.RUNE_POUCH_TYPE_4
	};

	static final int TOTAL_GP_GE_INDEX = 0;
	static final int TOTAL_GP_HA_INDEX = 1;
	static final int TOTAL_QTY_INDEX = 2;
	static final int NO_PROFIT_LOSS_TIME = -1;
}
