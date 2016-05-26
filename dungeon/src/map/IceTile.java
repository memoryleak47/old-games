package map;

import java.awt.image.BufferedImage;

import misc.ImageLoader;
import map.Tile;

public class IceTile extends Tile
{
	public BufferedImage getImage() { return ImageLoader.iceTile; }
}
