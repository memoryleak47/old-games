package base;

import core.Game;
import core.Player;
import core.ImageLoader;

public abstract class CharacterController extends Collision
{
	public float speed = 1;
	public final float STD_SPEED = 1;
	protected boolean ready = true;

	protected CharacterController(float x_, float y_)
	{
		x = x_;
		y = y_;
	}

	public void tick()
	{
		applyGravity(1);
		applyDrag();
		if (!ready)
			ready = true;

		if (speed > STD_SPEED)
		{
			if (this instanceof Player)
				Game.getEntities().add(new Animation(
					this, ((Player)this).image, 10*(speed-STD_SPEED), 10*(speed-STD_SPEED), 10));
			speed -= 0.001f;
		}

	}

	public void collideOthers()
	{
		inWater = false;

		for (int y = (int) (this.y); y < (int) (this.y + height); y++)
		{
			for (int x = (int) (this.x); x < (int) (this.x + width); x++)
			{
				if (Game.getLevel().getSolidTiles()[x / Game.TILESIZE][y / Game.TILESIZE].isWater())
					inWater = true;
				if (Game.getLevel().getSolidTiles()[x / Game.TILESIZE][y / Game.TILESIZE].isKill() && ready)
				{
					ready = false;
					die(2);
				}
				if (Game.getLevel().getSolidTiles()[x / Game.TILESIZE][y / Game.TILESIZE].isGlitch() && ready)
				{
					ready = false;
					flash(0, -2 * Game.TILESIZE * Game.SCALE);
				}
			}
		}
	}

	public void die(int damage_)
	{
		if ((Player) this == null)
			return;

		Player _p = (Player) this;
		_p.score -= damage_;
		x = _p.startX;
		y = _p.startY;
	}
}
