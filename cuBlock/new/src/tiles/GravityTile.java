package tiles;

import core.Game;
import core.ImageLoader;
import base.CharacterController;

public class GravityTile extends CharacterController
{

	public GravityTile(float x_, float y_)
	{
		super(x_, y_);
		x = x_ * Game.TILESIZE;
		y = y_ * Game.TILESIZE;
		width = Game.TILESIZE - 0.1f;
		height = Game.TILESIZE - 0.1f;
		image = ImageLoader.gravityTile;
	}

	public void tick()
	{
	super.tick();
	move(accX, accY);
	}
}
