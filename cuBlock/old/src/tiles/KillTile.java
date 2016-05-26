package tiles;

import main.ImageLoader;
import base.SolidTile;

public class KillTile extends SolidTile
{
	public KillTile()
	{
		image = ImageLoader.killTile;
	}

	public boolean isKill()
	{
		return true;
	}
	
	public boolean isSolid()
	{
		return false;
	}
}
