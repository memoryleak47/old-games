package Unimportant;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageLoader {

	static public BufferedImage playerImage, playerMissileImage;
	static public BufferedImage backgroundImage, healthBarImage, splatterImage;
	static public BufferedImage sunImage, sunLoadingImage, sunMissileImage;
	static public BufferedImage ghostImage, ghostMissileImage;
	static public BufferedImage mageImage, mageMissileImage;
	
	public ImageLoader()
	{
		playerImage = load("/Player/player.png");
		playerMissileImage = load("/Player/playerMissile.png");
		
		backgroundImage = load("/background.png");
		healthBarImage = load("/healthBar.png");
		splatterImage = load("/splatter.png");
		
		sunImage = load ("/Enemies/sun.png");
		sunMissileImage = load ("/Enemies/sunMissile.png");
		sunLoadingImage = load ("/Enemies/sunLoading.png");
		
		ghostImage = load ("/Enemies/ghost.png");
		ghostMissileImage = load ("/Enemies/ghostMissile.png");
		
		mageImage = load("/Enemies/mage.png");
		mageMissileImage = load("/Enemies/mageMissile.png");
	}
	
	public BufferedImage load(String path)
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
