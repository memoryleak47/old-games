package main;

import java.util.LinkedList;

import static main.Main.*;

class Pixel
{
	public static int SIZE, MAX_COLLS, CURVE_WAIT;
	public static double MAX_VEL;
	public static LinkedList<Pixel> pixels;
	public static LinkedList<Integer> curve;
	public double x, y, velX, velY;
	boolean ready = true;
	static int curveCounter = 0;

	public static void init()
	{
		curve = new LinkedList<Integer>();
		pixels = new LinkedList<Pixel>();
		for (int i = 0; i < START_AMOUNT; i++)
		{
			Pixel.pixels.add(new Pixel());
		}
		for (int i = 0; i < WIDTH; i++)
		{
			curve.add(Pixel.pixels.size());
		}
	}

	public Pixel()
	{
		ready = false;
		this.x = -Main.WIDTH + (Math.random()*2*Main.WIDTH);
		this.y = -Main.HEIGHT + (Math.random()*2*Main.HEIGHT);
		this.velX = -MAX_VEL + (Math.random()*2*MAX_VEL);
		this.velY = -MAX_VEL + (Math.random()*2*MAX_VEL);
	}

	public Pixel(double x, double y)
	{
		ready = false;
		this.x = x;
		this.y = y;
		this.velX = -MAX_VEL + (Math.random()*2*MAX_VEL);
		this.velY = -MAX_VEL + (Math.random()*2*MAX_VEL);
	}

	public Pixel(double x, double y, double velX, double velY)
	{
		ready = false;
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
	}

	public boolean collide(Pixel other)
	{
		return other != this && Math.abs(other.x - x) < SIZE && Math.abs(other.y - y) < SIZE;
	}

	public LinkedList<Pixel> getCollisions()
	{
		LinkedList<Pixel> collPixels = new LinkedList<Pixel>();
		for (Pixel pixel : pixels)
		{
			if (collide(pixel))
				collPixels.add(pixel);
		}
		return collPixels;
	}

	public LinkedList<Pixel> getReadyCollisions()
	{
		LinkedList<Pixel> collPixels = new LinkedList<Pixel>();
		for (Pixel pixel : pixels)
		{
			if (pixel.ready && collide(pixel))
				collPixels.add(pixel);
		}
		return collPixels;
	}

	public void onCreate()
	{
		ready = false;
		velX = -MAX_VEL + (Math.random()*2*MAX_VEL);
		velY = -MAX_VEL + (Math.random()*2*MAX_VEL);
	}

	public static void tick()
	{
		for (Pixel pixel : pixels)
		{
			if (!pixel.ready && pixel.getCollisions().size() == 0)
				pixel.ready = true;

			LinkedList<Pixel> collides = pixel.getReadyCollisions();
			if (pixel.getCollisions().size() > MAX_COLLS)
			{
				pixels.remove(pixel);
				break; //TODO remove
			} else if (pixel.ready && collides.size() > 0)
			{
				pixels.add(new Pixel(pixel.x, pixel.y));
				for (Pixel pixel2 : collides)
				{
					pixel2.onCreate();
				}
				pixel.onCreate();
				break; //TODO remove
			}

			pixel.x += pixel.velX;
			pixel.y += pixel.velY;

			if (pixel.x < 0)
			{
				pixel.x = 0;
				pixel.velX = Math.abs(pixel.velX);
			} else if (pixel.x > Main.WIDTH-SIZE)
			{
				pixel.x = Main.WIDTH-SIZE;
				pixel.velX = -Math.abs(pixel.velX);
			}

			if (pixel.y < 0)
			{
				pixel.y = 0;
				pixel.velY = Math.abs(pixel.velY);
			} else if (pixel.y > Main.HEIGHT-SIZE)
			{
				pixel.y = Main.HEIGHT-SIZE;
				pixel.velY = -Math.abs(pixel.velY);
			}
		}
		curveCounter++;
		if (curveCounter >= CURVE_WAIT)
		{
			curve.remove(curve.get(0));
			curve.add(Pixel.pixels.size());
			curveCounter = 0;
		}
	}

}
