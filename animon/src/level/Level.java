package level;

import helps.Vi2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import tileMain.Tile;

public class Level {

	private int[][] tiles;
	private Vi2 size;

	public Level(BufferedImage LevelImage) {
size = new Vi2(0, 0);
		size.x = LevelImage.getWidth();
		size.y = LevelImage.getHeight();
		loadLevel(LevelImage);
	}

	public void loadLevel(BufferedImage levelImage) {
		tiles = new int[size.x][size.y];

		for (int y = 0; y < size.y; y++) {
			for (int x = 0; x < size.x; x++) {
				Color c = new Color(levelImage.getRGB(x, y));
				String q = String.format("%02x%02x%02x", c.getRed(),
						c.getGreen(), c.getBlue());
				
				if (q.equals("00ff00")) // GRASS
				{
					tiles[x][y] = 1;
				} else if (q.equals("ff0000")) // DIRT
				{
					tiles[x][y] = 2;

				} else if (q.equals("7f7f7f")) // ROCK
				{
					tiles[x][y] = 3;
				} else if (q.equals("3f3f3f")) // STONE
				{
					tiles[x][y] = 4;
				} else if (q.equals("134c13")) // TREE
				{
					tiles[x][y] = 5;
				} else if (q.equals("b24c13")) // WALL
				{
					tiles[x][y] = 6;
				} else {
					tiles[x][y] = 1;
					System.out.println("WRONG NUMBER AT: " + x + " " + y);
				}

			}
		}
	}

	public void render(Graphics g) {

int x0 = Math.max(Game.getPlayer().getCameraOffset().x / (Game.TILESIZE * Game.SCALE),  0);
int y0 = Math.max(Game.getPlayer().getCameraOffset().y / (Game.TILESIZE * Game.SCALE),  0);
int x1 = Math.min((Game.getPlayer().getCameraOffset().x + Game.WIDTH * Game.SCALE) / (Game.TILESIZE * Game.SCALE) +1, size.x);
int y1 = Math.min((Game.getPlayer().getCameraOffset().y + Game.HEIGHT * Game.SCALE) / (Game.TILESIZE * Game.SCALE) +1, size.y);

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(g, x * Game.TILESIZE * Game.SCALE - Game.getPlayer().getCameraOffset().x,
						y * Game.TILESIZE * Game.SCALE - Game.getPlayer().getCameraOffset().y);
			}
		}
	}

	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= size.x || y >= size.y)
			return Tile.grass; //VOID
		
		if (tiles[x][y] == 1) {
			return Tile.grass;
		} else if (tiles[x][y] == 2) {
			return Tile.sand;
		} else if (tiles[x][y] == 3) {
			return Tile.rock;
		} else if (tiles[x][y] == 4) {
			return Tile.meadow;
		} else if (tiles[x][y] == 5) {
			return Tile.tree;
		} 
		System.out.println("MISTAKE-->sand");
		return Tile.sand;//Tile.grass;

	}
}
// }

