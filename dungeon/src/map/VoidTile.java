package map;

import java.awt.image.BufferedImage;

import misc.ImageLoader;
import map.Tile;

public class VoidTile extends Tile
{
	public BufferedImage getImage() { return ImageLoader.voidTile; }
	public boolean isObstacle() { return true; }
}
