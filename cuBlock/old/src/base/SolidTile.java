package base;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import tiles.AirTile;
import tiles.GlitchTile;
import tiles.KillTile;
import tiles.StoneTile;
import tiles.WaterTile;

public abstract class SolidTile
{
	protected BufferedImage	image;
	public static SolidTile	air		= new AirTile();
	public static SolidTile	stone	= new StoneTile();
	public static SolidTile	water	= new WaterTile();
	public static SolidTile	kill	= new KillTile();
	public static SolidTile	glitch	= new GlitchTile();

	public void render(Graphics g_, int x_, int y_)
	{
		g_.drawImage(image, x_, y_, Game.TILESIZE * Game.SCALE, Game.TILESIZE * Game.SCALE, null);
	}

	public boolean isSolid()
	{
		return true;
	}

	public boolean isDrawable()
	{
		return true;
	}

	public boolean isWater()
	{
		return false;
	}

	public boolean isKill()
	{
		return false;
	}

	public boolean isGlitch()
	{
		return false;
	}

	public boolean isStatic()
	{
		return true;
	}
}
