package com.flippingutilities.wealthtracking;

import com.flippingutilities.FlippingConfig;
//import com.flippingutilities.db.WealthDB;

import com.flippingutilities.controller.FlippingPlugin;
import com.flippingutilities.ui.wealthtracking.SessionProfitOverlay;
import com.flippingutilities.ui.wealthtracking.WealthPanel;
import static com.flippingutilities.wealthtracking.WealthTrackingIDs.*;

import com.google.gson.Gson;
import com.google.inject.Inject;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.game.ItemManager;


import net.runelite.client.eventbus.EventBus;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.RuneScapeProfileChanged;


import net.runelite.client.util.HotkeyListener;
import net.runelite.client.input.KeyListener;
import net.runelite.client.input.KeyManager;

import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
public class WealthTracker
{
	// inventory total
	// session profit
	// wealth total
	// time-based profit (session/day/week/month/qtr/all time)
	// graphing all these (combobox selector)
	// export data

	private OverlayManager overlayManager;
	private SessionProfitOverlay sessionProfitOverlay;

	@Inject	private Client client;
	@Inject private FlippingPlugin plugin;
	@Inject private FlippingConfig flippingConfig;
	@Inject private ClientThread clientThread;

	@Inject private Gson gson;
	@Inject private EventBus eventBus;
	@Inject	private KeyManager keyManager;
	@Inject	private ItemManager itemManager;

	@Getter final WealthPanel wealthPanel;

	public WealthTracker()
	{
		this.wealthPanel = createWealthPanel();
	}

	private WealthPanel createWealthPanel()
	{
		WealthPanel wp;
		try
		{
			wp = new WealthPanel();
		} catch(Exception e) {
			log.info("Error creating WealthPanel: {}", e.toString());
			throw e;
		}

		return wp;
	}

}