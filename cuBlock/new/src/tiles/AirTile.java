package tiles;

import base.SolidTile;

public class AirTile extends SolidTile
{
	public boolean isDrawable()
	{
		return false;
	}

	public boolean isSolid()
	{
		return false;
	}
}
