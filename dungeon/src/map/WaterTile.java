package map;

import java.awt.image.BufferedImage;

import misc.ImageLoader;
import map.Tile;

public class WaterTile extends Tile
{
	public BufferedImage getImage() { return ImageLoader.waterTile; }
}
