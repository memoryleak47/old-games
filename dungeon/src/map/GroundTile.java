package map;

import java.awt.image.BufferedImage;

import misc.ImageLoader;
import map.Tile;

public class GroundTile extends Tile
{
	public BufferedImage getImage() { return ImageLoader.groundTile; }
}
