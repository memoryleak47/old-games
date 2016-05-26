package graphic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageLoader {

	public BufferedImage grassImage, voidImage, stoneImage, fireImage, winImage, saveImage;
	public BufferedImage playerStand, playerGo, playerFly;

	public ImageLoader() {		
		playerStand = load("/player/stand.png");
		playerGo = load("/player/go.png");
		playerFly = load("/player/fly.png");
		
		grassImage = load("/tiles/grass.png");
		voidImage = load("/tiles/void.png");
		stoneImage = load("/tiles/stone.png");
		fireImage = load("/tiles/fire.png");
		winImage = load("/tiles/win.png");
		saveImage = load("/tiles/save.png");
	}

	public BufferedImage load(String path) {
		try {
			System.out.println(path);
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
