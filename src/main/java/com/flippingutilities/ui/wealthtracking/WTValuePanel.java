package com.flippingutilities.ui.wealthtracking;

import com.flippingutilities.controller.FlippingPlugin;
//import com.flippingutilities.model.Section;
import com.flippingutilities.ui.uiutilities.CustomColors;
import com.flippingutilities.ui.uiutilities.Icons;
import com.flippingutilities.wealthtracking.WTValueType;

import com.google.inject.Inject;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import lombok.Getter;
import lombok.Setter;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;

/**
 * [[[Value Label][Value]]
 * 	[[> Statistics]
 * 		[[Session ROI Label][Session ROI Value]]
 * 		[[Period ROI Label][Period ROI Value]
 * 	 	[Period Selector Combobox]]
 * 	[[> Chart]
 * 		[Chart]
 * 		[[Period Selector Combobox][Pin Chart Checkbox]]]
 */
public class WTValuePanel extends JPanel
{
	private static final String NUM_FORMAT = "%,d";

	@Inject	FlippingPlugin plugin;

	@Getter	@Setter	private WTValueType valueType;

	// I think most of these with setters could be final
	@Getter	@Setter	private JLabel valueTypeLabel = new JLabel();
	@Getter	@Setter	private JLabel valueLabel = new JLabel();

	@Getter	final JLabel statisticsSectionLabel = new JLabel("Statistics");
	@Getter	final JLabel sessionROILabel = new JLabel("Session ROI: ");
	@Getter	@Setter	private JLabel sessionROIValueLabel = new JLabel();

	@Getter	final JLabel periodROILabel = new JLabel("Period ROI: "); // todo make this change when period selected
	@Getter	@Setter	private JLabel periodROIValueLabel = new JLabel();
	@Getter	final JComboBox<String> periodSelectionCombobox = new JComboBox<>();

	@Getter	final JLabel chartSectionLabel = new JLabel("Chart");

	@Getter	final JLabel lastUpdatedLabel = new JLabel("Last Updated: ");
	@Getter	@Setter	JLabel lastUpdatedValueLabel = new JLabel();

	private final JPanel detailPanel;
	@Getter @Setter private boolean detailVisible;

	public WTValuePanel(WTValueType type)
	{
		super(false);

		this.valueType = type;
		switch (this.valueType)
		{
			case INVENTORY_VALUE:
				this.valueTypeLabel.setText("Inventory Value: ");
				this.detailVisible = true;
			case BANK_VALUE:
				this.valueTypeLabel.setText("Bank Value: ");
				this.detailVisible = false;
			case ACCOUNT_VALUE:
				this.valueTypeLabel.setText("Account Value: ");
				this.detailVisible = false;
		}

		setLayout(new BorderLayout());
		setBackground(CustomColors.DARK_GRAY);
		setBorder(new CompoundBorder(
			new MatteBorder(2, 2, 2, 2, ColorScheme.DARKER_GRAY_COLOR.darker()),
			new EmptyBorder(10, 5, 0, 0)
		));

		// set title
		// Create Overview Panel
		// create value label and value
		JPanel overviewPanel = createOverviewPanel();
		// Create Detail Panel
		detailPanel = createDetailPanel();
		// > detail/statistics section panel
			// create session ROI
			// create period ROI
			// create period combobox
		// > Chart section panel
			// chart
			// chart period combobox
		// Footer panel
			// Last Updated (as a placeholder)
		JPanel footerPanel = createFooterPanel();

		add(overviewPanel, BorderLayout.NORTH);
		add(detailPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);
	}

	private JPanel createOverviewPanel()
	{
		JPanel overview = new JPanel(new BorderLayout());
		overview.setBackground(getBackground());
		overview.add(valueTypeLabel, BorderLayout.WEST);
		overview.add(valueLabel, BorderLayout.EAST);


		return overview;
	}

	private JPanel createDetailPanel()
	{
		JPanel dp = new JPanel();
		return dp;
	}

	private JPanel createFooterPanel()
	{
		JPanel fp = new JPanel();
		return fp;
	}

	private JPanel createValueDetailsPanel()
	{

		JPanel sectionPanel = new JPanel(new BorderLayout());
		sectionPanel.setBackground(getBackground());

		JLabel arrowIconLabel = new JLabel(valueDetails.isDefaultExpanded() ? Icons.OPEN_ICON : Icons.CLOSE_ICON);
		arrowIconLabel.setVerticalAlignment(JLabel.NORTH);
		arrowIconLabel.setFont(FontManager.getRunescapeBoldFont());
		arrowIconLabel.setBorder(new EmptyBorder(0, 0, 0, 5));

		sectionPanel.add(arrowIconLabel, BorderLayout.WEST);

		JPanel sectionItemsPanel = new JPanel();
		sectionItemsPanel.setLayout(new BoxLayout(sectionItemsPanel, BoxLayout.Y_AXIS));
		sectionItemsPanel.setBackground(getBackground());
		if (!section.isDefaultExpanded())
		{
			arrowIconLabel.setText(section.getName());
			sectionItemsPanel.setVisible(false);
		}

		sectionPanel.add(sectionItemsPanel, BorderLayout.CENTER);
		arrowIconLabel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				if (sectionItemsPanel.isVisible())
				{
					sectionItemsPanel.setVisible(false);
					arrowIconLabel.setIcon(Icons.CLOSE_ICON);
					arrowIconLabel.setText(section.getName());
				}
				else
				{
					sectionItemsPanel.setVisible(true);
					arrowIconLabel.setIcon(Icons.OPEN_ICON);
					arrowIconLabel.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				super.mouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				super.mouseExited(e);
			}
		});

		List<String> labelsToShow = new ArrayList<>();
		for (String labelName : section.getLabels().keySet())
		{
			if (section.getLabels().get(labelName))
			{
				labelsToShow.add(labelName);
			}
		}
		boolean isFirstInPair = true;
		for (String labelName : labelsToShow)
		{
			JPanel panel = JPanel();

			if (isFirstInPair)
			{
				panel.setBorder(new EmptyBorder(6, 0, 3, 0));
			}
			else
			{
				panel.setBorder(new EmptyBorder(2, 0, 8, 0));
			}
			isFirstInPair = !isFirstInPair;
			sectionItemsPanel.add(panel);
		}

		return sectionPanel;
	}


	private void detailSwapVisibility()
	{
		this.detailPanel.setVisible(!this.detailVisible);
	}

}



