package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.controller.FlippingPlugin;
import com.flippingutilities.wealthtracking.WealthTracker;
import com.flippingutilities.wealthtracking.model.WTValueType;
import com.flippingutilities.wealthtracking.model.WealthSnapshot;
import java.awt.BorderLayout;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.runelite.client.ui.ColorScheme;

/**
 * The main panel for the wealth tracking feature. It serves as the container for all
 * other wealth tracking UI components like value panels and charts. It gets its data
 * from the WealthTracker.
 */
@Singleton
public class WealthPanel extends JPanel {

    private final WealthTracker wealthTracker;
    private final FlippingPlugin plugin;
    private final JLabel placeholderLabel; // A temporary label to show something is happening

    @Inject
    public WealthPanel(FlippingPlugin plugin, WealthTracker wealthTracker) {
        this.plugin = plugin;
        this.wealthTracker = wealthTracker;

        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setLayout(new BorderLayout());

        placeholderLabel = new JLabel("Wealth Tracker UI will be here.");
        placeholderLabel.setHorizontalAlignment(JLabel.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);

        // In the future, this is where we would create and add:
        // - A header panel for summary stats (e.g., ROI)
        // - The main scrollable panel of WTValuePanels
        // - A footer panel for the chart (WTChartPanel)
    }

    /**
     * Called by the WealthTracker when new data is available. This method will be responsible
     * for repainting the panel and updating its child components with the new information.
     */
    public void update() {
        WealthSnapshot latestSnapshot = wealthTracker.getAccountWealth().getLatestSnapshot();
        if (latestSnapshot != null) {
            long overallValue = latestSnapshot.getWealthValues().getOrDefault(WTValueType.OVERALL, 0L);
            placeholderLabel.setText("Total Wealth: " + overallValue + " gp");
        }
    }
}
