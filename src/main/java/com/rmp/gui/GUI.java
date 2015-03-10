package main.java.com.rmp.gui;

import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;

public class GUI extends JFrame {

	private static final Point CENTER_POINT = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getCenterPoint();
	private Panel panel = new Panel();

	public GUI() {
		super("FDM");
		setBounds(CENTER_POINT.x - panel.getWidth() / 2,
				CENTER_POINT.y - panel.getHeight() / 2, panel.getWidth(),
				panel.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new Panel());
		pack();
		setVisible(true);
		setResizable(false);

	}

	public static void main(String[] args) {
		new GUI();
	}
}
