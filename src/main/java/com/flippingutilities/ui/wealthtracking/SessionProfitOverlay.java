package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.wealthtracking.WealthTracker;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;

/**
 * An overlay to show the profit/loss for the current session.
 * This is a placeholder and will need to be properly implemented to calculate
 * session profit based on the snapshots from the WealthTracker.
 */
public class SessionProfitOverlay extends Overlay {
    private final WealthTracker wealthTracker;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    private SessionProfitOverlay(WealthTracker wealthTracker) {
        this.wealthTracker = wealthTracker;
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.getChildren().clear();

        //TODO: Implement logic to calculate session profit.
        //This would involve getting the first and last snapshot for the current session
        //from the WealthTracker and calculating the difference.
        long sessionProfit = 0; //Placeholder value

        panelComponent.getChildren().add(LineComponent.builder()
            .left("Session Profit:")
            .right(sessionProfit + " gp")
            .build());

        return panelComponent.render(graphics);
    }
}
