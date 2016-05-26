package tiles;

import core.ImageLoader;
import base.SolidTile;

public class GlitchTile extends SolidTile
{
	public GlitchTile()
	{
		image = ImageLoader.glitchTile;
	}

	public boolean isGlitch()
	{
		return true;
	}
	
	public boolean isSolid()
	{
		return false;
	}

}
