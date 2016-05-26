package base;

import main.Game;
import main.Player;

public abstract class CharacterController extends Collision
{
	public float		speed		= 1;
	public final float	STD_SPEED	= 1;
	protected boolean	ready		= true;

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
			speed -= 0.01f;
		}

	}

	public void collideOthers()
	{
		inWater = false;

		for (int y = (int) (this.y); y < (int) (this.y + height); y++)
		{
			for (int x = (int) (this.x); x < (int) (this.x + width); x++)
			{
				if (Game.getLevel().solidTiles[x / Game.TILESIZE][y / Game.TILESIZE].isWater())
					inWater = true;
				if (Game.getLevel().solidTiles[x / Game.TILESIZE][y / Game.TILESIZE].isKill() && ready)
				{
					ready = false;
					die(2);
				}
				if (Game.getLevel().solidTiles[x / Game.TILESIZE][y / Game.TILESIZE].isGlitch() && ready)
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