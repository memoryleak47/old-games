package tileMain;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

import tiles.GrassTile;
import tiles.MeadowTile;
import tiles.RockTile;
import tiles.SandTile;
import tiles.TreeTile;

public abstract class Tile {
	protected BufferedImage image;
		public static Tile grass = new GrassTile();
		public static Tile sand = new SandTile();
		public static Tile rock = new RockTile();
		public static Tile meadow = new MeadowTile();
		public static Tile tree = new TreeTile();
		
	public Tile(String path) {
		image = Game.getLoader().load("/tiles/" + path + ".png");
	}

	public abstract void tick();

	public void render(Graphics g, int x, int y)
	{
			g.drawImage(image, x, y, Game.TILESIZE * Game.SCALE,
					Game.TILESIZE * Game.SCALE, null);	
	}
	
	public boolean isSolid()
	{
	return false;	
	}
	
	}


