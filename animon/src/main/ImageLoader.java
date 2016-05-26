package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class ImageLoader {
    public static BufferedImage healthBar, energyBar, expBar; 
    
    public ImageLoader()
    {
	healthBar = load("/bars/health.png");
	energyBar = load("/bars/energy.png");
	expBar = load("/bars/exp.png");
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
