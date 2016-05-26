package map;

import java.util.LinkedList;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.Random;

import map.Tile;
import object.Entity;
import core.Screen;
import misc.ImageLoader;
import object.Boulder;
import object.Player;
import object.Door;
import static core.Game.TILESIZE;

public class Map
{
	private Tile[][] tiles;
	public LinkedList<Entity> entities;
	public final int WIDTH, HEIGHT;
	private BufferedImage mapImage;

	public Map(String path)
	{
		BufferedImage image = ImageLoader.load(path);
		WIDTH = image.getWidth();
		HEIGHT = image.getHeight();
		entities = new LinkedList<Entity>();
		entities.add(new Player((int) (WIDTH/2), HEIGHT-1));
		tiles = new Tile[WIDTH][HEIGHT];

		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				Color c = new Color(image.getRGB(x, y));
				String q = String.format("%02x%02x%02x", c.getRed(),
						c.getGreen(), c.getBlue());
				
				if (q.equals("0000ff")) // Water
				{
					tiles[x][y] = Tile.waterTile;
			 	} else if (q.equals("555555")) // Ground
				{
					tiles[x][y] = Tile.groundTile;
				} else if (q.equals("770000")) // Rock
				{
					tiles[x][y] = Tile.rockTile;
				} else if (q.equals("ccccff")) // Ice
				{
					tiles[x][y] = Tile.iceTile;
				} else if (q.equals("00ff00")) // Door
				{
					tiles[x][y] = Tile.groundTile;
					entities.add(new Door(x, y));
				} else if (q.equals("000000")) // Boulder
				{
					tiles[x][y] = Tile.groundTile;
					entities.add(new Boulder(x, y));
				} else {
					tiles[x][y] = Tile.voidTile;
					System.out.println(q + " AT: " + x + " " + y); // DEBUG
				}

			}
		}
		reloadTiles();
	}

	public void reloadTiles()
	{
		mapImage = new BufferedImage(WIDTH * TILESIZE, HEIGHT * TILESIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics g = mapImage.getGraphics();

		for (int x = 0; x < WIDTH; x++)
			for (int y = 0; y < HEIGHT; y++)
			{
				g.drawImage(tiles[x][y].getImage(), x*TILESIZE, y*TILESIZE, TILESIZE, TILESIZE, null);
			}
		g.dispose();
		
	}

	public void render()
	{
		Screen.draw(mapImage, 0, 0, WIDTH*TILESIZE, HEIGHT*TILESIZE);
	}

	public boolean inRange(int x, int y)
	{
		boolean result = (x>=0 && x<WIDTH && y>=0 && y<HEIGHT);
		if (!result)
			System.out.println("out of Range of tiles. x={" + x + "} y={" + y + "}"); // DEBUG
		return result;
	}

	public void push(int x, int y, int direction) // executed whenever the player runs against an obstacle
							// not including ice-movement-collision
	{
		if (!inRange(x, y))
			return;

		tiles[x][y].push(direction);
		for (Entity entity : entities)
		{
			if (entity.roundX() == x && entity.roundY() == y)
				entity.push(direction);
		}
	}

	public Player getPlayer()
	{
		if (entities.get(0) instanceof Player)
			return (Player) entities.get(0);
		System.out.println("no Player found"); // DEBUG
		return null;
	}

	public boolean obstacleAt(int x, int y)
	{
		if (!inRange(x, y)) // if this is outta map
			return true; // you shouldn't go there -> its obstacle

		if (tiles[x][y].isObstacle()) // if the tile is an obstacle like 'rock' or 'void'
			return true;

		for (Entity entity : entities) // for all entities
		{
			if (entity.isObstacle() && entity.x() == x && entity.y() == y) // if theres an obstacle entity on the block (not between)
				return true;
		}
		return false; // if none of these... no obstacle
	}

	public Tile tileAt(int x, int y)
	{
		if (!inRange(x, y))
			return null;
		return tiles[x][y];
	}

	public void setTile(int x, int y, Tile t)
	{
		if (!inRange(x, y))
			return;
		tiles[x][y] = t;
		reloadTiles();
	}
}
