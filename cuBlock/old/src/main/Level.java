package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import tiles.GravityTile;
import base.Entity;
import base.SolidTile;

public class Level
{

	private int				width, height;
	public SolidTile[][]	solidTiles;
	private BufferedImage staticTilesImage;

	public Level(BufferedImage LevelImage_)
	{
		width = LevelImage_.getWidth();
		height = LevelImage_.getHeight();	
		staticTilesImage = new BufferedImage(Game.WIDTH * Game.SCALE * Game.TILESIZE, Game.HEIGHT * Game.SCALE * Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
		loadLevel(LevelImage_);
	}

	public void render(Graphics g_)
	{
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				if (!(solidTiles[x][y]).isStatic() && (solidTiles[x][y]).isDrawable())
					solidTiles[x][y].render(g_, x * Game.TILESIZE * Game.SCALE - Entity.getCameraOffsetX(), y * Game.TILESIZE * Game.SCALE - Entity.getCameraOffsetY());
			}
		}
		g_.drawImage(staticTilesImage, 0, 0, Game.WIDTH * Game.SCALE * Game.TILESIZE, Game.HEIGHT * Game.SCALE * Game.TILESIZE,  null);
	}

	public void loadLevel(BufferedImage levelImage_)
	{
		solidTiles = new SolidTile[width][height];
		Graphics2D g2 = staticTilesImage.createGraphics();

		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				Color c = new Color(levelImage_.getRGB(x, y));
				String q = String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());

				solidTiles[x][y] = SolidTile.air;
				if (q.equals("000000"))
				{
					(solidTiles[x][y] = SolidTile.stone).render(g2, x*Game.TILESIZE*Game.SCALE, y*Game.TILESIZE*Game.SCALE);
				} else
					if (q.equals("cccccc"))
					{
						Game.getEntities().add(new GravityTile(x, y));
					} else
						if (q.equals("0000cc"))
						{
							(solidTiles[x][y] = SolidTile.water).render(g2, x*Game.TILESIZE*Game.SCALE, y*Game.SCALE*Game.TILESIZE);
						} else
							if (q.equals("cccc00"))
							{
								(solidTiles[x][y] = SolidTile.kill).render(g2, x*Game.TILESIZE*Game.SCALE, y*Game.SCALE*Game.TILESIZE);
							} else
								if (q.equals("cc00cc"))
								{
									(solidTiles[x][y] = SolidTile.glitch).render(g2, x*Game.TILESIZE*Game.SCALE, y*Game.SCALE*Game.TILESIZE);
								}

			}
		}
	}

	public float getHeight()
	{
		return height;
	}

	public float getWidth()
	{
		return width;
	}
}
