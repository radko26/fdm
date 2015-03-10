package main.java.com.rmp.gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCopyEngine {

	public BufferedImage copy(BufferedImage initialImage) {
		BufferedImage copyImage = new BufferedImage(initialImage.getWidth(),
				initialImage.getHeight(), initialImage.getType());
		Graphics2D g = copyImage.createGraphics();

		g.drawImage(initialImage, 0, 0, null);
		g.dispose();
		return copyImage;
	}

	public File saveToFile(BufferedImage image, File file) throws IOException {
		String tokens[] = file.getName().split("\\.");
		String ext = tokens[tokens.length - 1];
		ImageIO.write(image, ext, file);

		return file;
	}
}
