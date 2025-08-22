package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.controller.FlippingPlugin;
import com.flippingutilities.utilities.Jwt;
import com.flippingutilities.wealthtracking.WTValueType;
import com.google.inject.Inject;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import net.runelite.client.ui.ColorScheme;

public class WealthPanel extends JPanel
{
	private static final String VALUES_PANEL = "VALUES_PANEL";
	//private static final String WELCOME_PANEL = "WELCOME_PANEL";

	@Inject private FlippingPlugin plugin;

	public final CardLayout valuesCardLayout = new CardLayout();
	public final CardLayout chartCardLayout = new CardLayout();

	//private final JPanel WTHeaderPanel = new JPanel();

	private final JPanel wtValuesPanel = new JPanel();
	private final JPanel wtValuesContainer = new JPanel(valuesCardLayout);

	private final JPanel wtPinnedChartPanel = new JPanel();
	private final JPanel wtPinnedChartContainer = new JPanel(chartCardLayout);

	private final JPanel wtHeaderPanel;
	private final JPanel wtFooterPanel;

	//contains the value panels
	private ArrayList<WTValuePanel> activeValueBoxes = new ArrayList<>();

	public WealthPanel()
	{
		super(false);

		setLayout(new BorderLayout());
		setBackground(ColorScheme.DARK_GRAY_COLOR);


		// set up the scrollable values panel
		ArrayList<WTValuePanel> valuePanels = new ArrayList<>();
		for(WTValueType vt : WTValueType.values())
		{
			WTValuePanel valuePanel = new WTValuePanel(vt);
			valuePanels.add(valuePanel);
			wtValuesPanel.add(valuePanel);
		}
		wtValuesPanel.setLayout(new BoxLayout(wtValuesPanel, BoxLayout.Y_AXIS));
		wtValuesPanel.setBorder(new EmptyBorder(0, 8, 0, 7));
		wtValuesPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);
		JPanel wrapper = new JPanel(new BorderLayout());
		wrapper.setBackground(ColorScheme.DARK_GRAY_COLOR);
		wrapper.add(wtValuesPanel, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(wrapper);
		scrollPane.setBackground(ColorScheme.DARK_GRAY_COLOR);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(2, 0));
		wtValuesContainer.add(scrollPane, VALUES_PANEL);

		// setup pinned chart
		//wtPinnedChartPanel

		// setup header and footer
		wtHeaderPanel = new JPanel(new BorderLayout());
		wtHeaderPanel.setBorder(new EmptyBorder(0,0,2,0));
		// todo populate


		wtFooterPanel  = new JPanel(new BorderLayout());
		wtFooterPanel.setBorder(new EmptyBorder(2,0,0,0 ));
		// todo populate: chart, chart period menu, chart
		wtFooterPanel.add(wtPinnedChartContainer, BorderLayout.CENTER);

		//To switch between greeting and items panels
		valuesCardLayout.show(wtValuesContainer, VALUES_PANEL);
		add(wtHeaderPanel, BorderLayout.NORTH);
		add(wtValuesContainer, BorderLayout.CENTER);
		add(wtFooterPanel, BorderLayout.SOUTH);

		setBorder(new EmptyBorder(5,0,0,0));
	}

	/**
	 * Creates and renders the panel:
	 * [[top: current overall, session ROI, weekly ROI]
	 *  [scroll panel: , inventory, bank]
	 *  [chart panel: chart type combobox, chart]]
	 *
	 */
//	private ArrayList<WTValuePanel> getActiveSubPanelBoxes()
//	{
//		SwingUtilities.invokeLater(()->
//		{
//			activeSubPanelBoxes.forEach(p -> p.popup.setVisible(false));
//			activeSubPanelBoxes.clear();
//			wtValuePanel.removeAll();
//
//			// inventory
//			// account value
//			//
//		});
//	}

}
