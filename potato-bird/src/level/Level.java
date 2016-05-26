package level;

import main.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Level {

	public static int[][] tiles;
	private int w, h;
	private BufferedImage map;
	public int level;
	
	public Level(BufferedImage LevelImage) {
		level = 0;
		w = LevelImage.getWidth();
		h = LevelImage.getHeight();
		map = new BufferedImage(w * Game.SCALE * Game.TILESIZE, h * Game.SCALE * Game.TILESIZE, BufferedImage.TYPE_INT_ARGB);
		loadLevel(LevelImage);
	}

	public void loadLevel(BufferedImage levelImage) {
		tiles = new int[w][h];
		Graphics2D g2 = map.createGraphics();

		for (int y = 0; y < h; y++)
		{
			for (int x = 0; x < w; x++)
			{
				Color c = new Color(levelImage.getRGB(x, y));
				String q = String.format("%02x%02x%02x", c.getRed(),
						c.getGreen(), c.getBlue());
				switch (q)
				{
					case "00ff00": {
						tiles[x][y] = 1;
						break;
					}
					case "7f7f7f": {
						tiles[x][y] = 2;
						break;
					}
					case "ff0000":
					{
						tiles[x][y] = 3;
						break;
					}
					case "ffffff":
					{
						tiles[x][y] = 4;
						break;
					}
					case "ffff00":
					{
						tiles[x][y] = 5;
						break;
					}
					default: {
						tiles[x][y] = 0;
						break;
					}
				}
				getTile(x, y).render(g2, x * Game.TILESIZE * Game.SCALE, y * Game.TILESIZE * Game.SCALE);
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(map,
			- Game.getPlayer().getXo(),
			- Game.getPlayer().getYo(),
			Game.SCALE * Game.TILESIZE * w,
			Game.SCALE * Game.TILESIZE * h,
			null);
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= w || y >= h)
			return Tile.voidTile; // VOID

		
		switch(tiles[x][y])
		{
			case 1:
			{
				return Tile.grassTile;	
			}
			case 2:
			{
				return Tile.stoneTile;
			}
			case 3:
			{
				return Tile.fireTile;
			}
			case 4:
			{
				return Tile.winTile;
			}
			case 5:
			{
				return Tile.saveTile;
			}
		}
		return Tile.voidTile;

	}
	
	public void nextLevel()
	{
		level++;
		switch (level)
		{
			case 0:
			{
				loadLevel(Game.getLoader().load("/level/level1.png")); 
				break;
			}
			case 1:
			{
				loadLevel(Game.getLoader().load("/level/level2.png"));
				break;
			}
			case 2:
			{
				loadLevel(Game.getLoader().load("/level/level3.png"));
				break;
			}
			case 3:
			{
				loadLevel(Game.getLoader().load("/level/level4.png"));
				level = -1;
				break;
			}
		}
		
		
	}
}
