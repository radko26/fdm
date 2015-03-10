package main.java.com.rmp.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
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
	private JLabel errorLabel = new JLabel("");

	public Panel() {
		chooser = new JFileChooser();
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		add(dateLabel);
		dateField.setPreferredSize(new Dimension(180, 20));
		add(dateField);

		launchChooserButton.addActionListener(new ClickAction());
		add(launchChooserButton);

		add(errorLabel);

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

		private ImageCopyEngine engine = new ImageCopyEngine();

		@Override
		public void actionPerformed(ActionEvent e) {

			errorLabel.setText("");
			SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
			Date newDate;
			try {
				newDate = format.parse(dateField.getText());
				chooser.setMultiSelectionEnabled(true);
				chooser.setDialogTitle("ChangeDateAttribute");
				if (lastDir != null) {
					chooser.setCurrentDirectory(lastDir);
				}

				int result = chooser.showDialog(getPanel(), "Change");
				if (result == JFileChooser.APPROVE_OPTION) {
					for (File a : chooser.getSelectedFiles()) {

						try {
							String dateTokens[] = dateField.getText()
									.toString().split("\\.");
							StringBuilder newName = new StringBuilder();
							for (String token : dateTokens) {
								newName.append(token);
							}
							newName.append(a.getName().substring(8));

							File outputDir = new File(a.getParent() + "/output");
							if (!outputDir.exists()) {
								outputDir.mkdir();
							}

							BufferedImage initialImage = ImageIO.read(a);
							BufferedImage copyImage = engine.copy(initialImage);

							File newCopyFile = new File(outputDir.getPath()
									+ "/" + newName.toString());

							newCopyFile = engine.saveToFile(copyImage,
									newCopyFile);

							attrModifier.modify(newDate, newCopyFile);

						} catch (IOException e1) {
							errorLabel.setText("Ooops, something went wrong.");
							System.out.println(e1.getMessage());
						}
					}
					lastDir = chooser.getCurrentDirectory();
				}

			} catch (ParseException e2) {
				errorLabel.setText("Bad date format!");
			}

		}
	}

}
