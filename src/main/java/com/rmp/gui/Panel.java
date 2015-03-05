package main.java.com.rmp.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel {
	private static final int PANEL_HEIGHT = 100;
	private static final int PANEL_WIDTH = 200;
	private JFileChooser chooser;
	private JButton launchChooserButton = new JButton("Launch");
	private JLabel dateLabel = new JLabel("Date format example 06.05.1997");
	private JTextField dateField = new JTextField();
	private FileModifier attrModifier = new FileModifier();
	private File lastDir;

	public Panel() {
		chooser = new JFileChooser();
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		add(dateLabel);
		dateField.setPreferredSize(new Dimension(180, 20));
		add(dateField);

		launchChooserButton.addActionListener(new ClickAction());
		add(launchChooserButton);

	}

	public int getHeight() {
		return PANEL_HEIGHT;
	}

	public int getWidth() {
		return PANEL_WIDTH;
	}

	private JPanel getPanel() {
		return this;
	}

	private class ClickAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			chooser.setMultiSelectionEnabled(true);
			chooser.setDialogTitle("ChangeDateAttribute");
			if (lastDir != null) {
				chooser.setCurrentDirectory(lastDir);
			}

			int result = chooser.showDialog(getPanel(), "Change");
			if (result == JFileChooser.APPROVE_OPTION) {
				for (File a : chooser.getSelectedFiles()) {
					System.out.println(a.getName());
					try {
						attrModifier.modify(dateField.getText(), a);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				lastDir = chooser.getCurrentDirectory();
			}

		}

	}

}
