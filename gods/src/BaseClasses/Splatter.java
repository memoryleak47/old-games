package BaseClasses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Important.Game;
import Unimportant.ImageLoader;

public class Splatter {

	BufferedImage image;
	private float x, y;
	private boolean dead;
	private int lifeTime;
	private final int SIZE = 20;
	
	public Splatter(float x, float y)
	{
		lifeTime = 20;
		dead = false;
		Game.getLoader();
		image = ImageLoader.splatterImage;
		this.x = x;
		this.y = y;
		
		x -= SIZE/2;
		y -= SIZE/2;
	}
	
	public void tick()
	{
		lifeTime--;
		if(lifeTime <= 0)
			dead = true;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(image, (int) x, (int) y, SIZE, SIZE, null);
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	
	
	
}
