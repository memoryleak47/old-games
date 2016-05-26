package other.graphic;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public final class ImageLoader //no construcor + final -> only static use
{
	public static BufferedImage
		player, damageBlock, redBlock, greenBlock, blueBlock, yellowBlock;

	static { //initialization block
		try {
			player = loadImage("/player.png");
			damageBlock = loadImage("/damageBlock.png");
			redBlock = loadImage("/redBlock.png");
			greenBlock = loadImage("/greenBlock.png");
			blueBlock = loadImage("/blueBlock.png");
			yellowBlock = loadImage("/yellowBlock.png");
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static BufferedImage loadImage(String path) throws IOException
	{
		return ImageIO.read(ImageLoader.class.getResource(path));
	}	
}
