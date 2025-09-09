package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.wealthtracking.model.AccountWealth;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.runelite.client.ui.ColorScheme;

/**
 * A panel designated for displaying a chart of wealth over time.
 * For now, this is a placeholder. The actual chart implementation can be added later.
 */
public class WTChartPanel extends JPanel {

    public WTChartPanel() {
        setBackground(ColorScheme.DARKER_GRAY_COLOR);
        setLayout(new BorderLayout());
        //TODO: Add a charting library (e.g., JFreeChart) and render the wealth history.
    }

    /**
     * Updates the chart with new historical data.
     * @param accountWealth The object containing the full history of wealth snapshots.
     */
    public void updateChart(AccountWealth accountWealth) {
        //This is where the logic to redraw the chart with the new data would go.
    }
}
