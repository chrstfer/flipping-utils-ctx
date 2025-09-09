package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.wealthtracking.model.WTValueType;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.util.QuantityFormatter;

/**
 * A simple panel to display a single wealth value, such as "Bank Value" or "Inventory Value".
 * It consists of a label for the name and a label for the value.
 */
public class WTValuePanel extends JPanel {
    private final WTValueType valueType;
    private final JLabel nameLabel;
    private final JLabel valueLabel;

    public WTValuePanel(WTValueType valueType) {
        this.valueType = valueType;
        setLayout(new BorderLayout());
        setBackground(ColorScheme.DARKER_GRAY_COLOR);

        nameLabel = new JLabel(valueType.getName());
        nameLabel.setForeground(ColorScheme.LIGHT_GRAY_COLOR);

        valueLabel = new JLabel("0 gp");
        valueLabel.setForeground(ColorScheme.GRAND_EXCHANGE_PRICE);

        add(nameLabel, BorderLayout.WEST);
        add(valueLabel, BorderLayout.EAST);
    }

    /**
     * Updates the value displayed on the panel.
     * @param value the new value to display.
     */
    public void updateValue(long value) {
        valueLabel.setText(QuantityFormatter.formatNumber(value) + " gp");
        // In a real implementation, we might also show a +/- change since the last update.
    }
}
