package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.controller.FlippingPlugin;
import com.flippingutilities.wealthtracking.model.WTValueType;
import com.flippingutilities.wealthtracking.model.WealthSnapshot;
import com.flippingutilities.wealthtracking.model.WealthSnapshotEvent;
import java.awt.BorderLayout;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.QuantityFormatter;

/**
 * The main panel for the wealth tracking feature. It serves as the container for all
 * other wealth tracking UI components like value panels and charts.
 */
@Singleton
public class WealthPanel extends JPanel {

    private final JLabel placeholderLabel;

    @Inject
    public WealthPanel(FlippingPlugin plugin) {
        setBackground(ColorScheme.DARK_GRAY_COLOR);
        setLayout(new BorderLayout());

        placeholderLabel = new JLabel("Waiting for first wealth snapshot...");
        placeholderLabel.setHorizontalAlignment(JLabel.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);
    }

    @Subscribe
    public void onWealthSnapshotEvent(WealthSnapshotEvent event) {
        WealthSnapshot latestSnapshot = event.getNewSnapshot();
        if (latestSnapshot != null) {
            long overallValue = latestSnapshot.getWealthValues().getOrDefault(WTValueType.OVERALL, 0L);
            placeholderLabel.setText("Total Wealth: " + QuantityFormatter.formatNumber(overallValue) + " gp");
        }
    }
}
