package main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.io.File;

public class ImageLoader
{
	public static BufferedImage	player1, player2, player3;
	public static BufferedImage	background;
	public static BufferedImage	stoneTile, gravityTile, waterTile, killTile, glitchTile;
	public static BufferedImage	coinItem, gravityItem, speedItem;
	public static BufferedImage	runnerParticle;

	public ImageLoader()
	{
		player1 = load("/player/player1.png");
		player2 = load("/player/player2.png");
		player3 = load("/player/player3.png");

		background = load("/background.png");

		stoneTile = load("/tiles/stoneTile.png");
		gravityTile = load("/tiles/gravityTile.png");
		waterTile = load("/tiles/waterTile.png");
		killTile = load("/tiles/killTile.png");
		glitchTile = load("/tiles/glitchTile.png");

		coinItem = load("/items/coinItem.png");
		gravityItem = load("/items/gravityItem.png");
		speedItem = load("/items/speedItem.png");

		runnerParticle = load("/particles/runnerParticle.png");
	}

	public BufferedImage load(String path)
	{
		try
		{
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
