package map;

import java.awt.image.BufferedImage;

import misc.ImageLoader;
import map.Tile;

public class RockTile extends Tile
{
	public BufferedImage getImage() { return ImageLoader.rockTile; }
	public boolean isObstacle() { return true; }
}
