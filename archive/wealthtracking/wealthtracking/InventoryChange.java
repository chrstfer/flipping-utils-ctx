package com.flippingutilities.wealthtracking;

// item id (indexed), qty, type: enum{pickup,trade,used,dropped}, bank io: bool, GE io: bool (indexed), geValueAtChange, haValueAtChange, timestamp, sessionID (indexed), invChangeID: pkey


// class storing information about a change in the inventory
// qty: quantity gained (positive) or lost (negative), with |qty|>1 meaning stackable
// type: pickup (item was picked up from the ground or given the item by an npc), trade (traded from GE or other player), used (item was a consumable and got used), dropped (item was dropped on the ground)
// time and sequence tracking (invChangeID), as well as session sequence tracking (sessionID)
// persisted in db
public class InventoryChange
{

}




