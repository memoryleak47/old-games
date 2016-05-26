package misc;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader
{
	public static BufferedImage voidTile = load("/tile/void.png");
	public static BufferedImage waterTile = load("/tile/water.png");
	public static BufferedImage groundTile = load("/tile/ground.png");
	public static BufferedImage rockTile = load("/tile/rock.png");
	public static BufferedImage iceTile = load("/tile/ice.png");
	public static BufferedImage waterBoulderTile = load("/tile/waterboulder.png");
	public static BufferedImage boulder = load("/object/boulder.png");
	public static BufferedImage door = load("/object/door.png");
	public static BufferedImage player = load("/object/player.png");

	public static BufferedImage load(String path) // Map can use it
	{
		try
		{
			BufferedImage image = ImageIO.read(ImageLoader.class.getResource(path));
			System.out.println("success loading " + path); // DEBUG
			return image;
		} catch (Exception e)
		{
			System.out.println("error loading " + path); // DEBUG
		}
		return null;
	}
}
