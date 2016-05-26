package map;

import java.awt.image.BufferedImage;

import object.Entity;
import map.WaterTile;
import map.GroundTile;
import map.RockTile;
import map.IceTile;
import map.VoidTile;
import map.WaterBoulderTile;

public abstract class Tile
{
	static Tile waterTile = new WaterTile();
	static Tile groundTile = new GroundTile();
	static Tile rockTile = new RockTile();
	static Tile iceTile = new IceTile();
	static Tile voidTile = new VoidTile();
	public static WaterBoulderTile waterBoulderTile = new WaterBoulderTile();

	public void push(int direction) {} // executed when player runs against this obstacle

	public abstract BufferedImage getImage();
	public boolean isObstacle() { return false; }
}
