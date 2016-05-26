package tiles;

import main.ImageLoader;
import base.SolidTile;

public class WaterTile extends SolidTile
{
	public WaterTile()
	{
		image = ImageLoader.waterTile;
	}

	public boolean isWater()
	{
		return true;
	}

	public boolean isSolid()
	{
		return false;
	}
}
