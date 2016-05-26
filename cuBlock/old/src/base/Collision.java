package base;

import main.Game;

public abstract class Collision extends Entity
{
	protected boolean	isGrounded;
	public boolean		inWater;

	public void move(float accX_, float accY_)
	{
		if (!this.collideTerrain(accX_, 0))
		{
			x += accX_;
		} else
			accX = 0;
		if (!this.collideTerrain(0, accY_))
		{
			y += accY_;
			isGrounded = false;
		} else
		{
			if (inWater)
				accY = 0;
			if ((accY_ / Math.abs(accY_)) == (GRAVITY / Math.abs(GRAVITY)))
			{
				accY = 0;
				isGrounded = true;
			} else
			{
				isGrounded = false;
			}
		}

	}

	public void flash(float accX_, float accY_)
	{
		if (!collideTerrain(accX_, 0))
		{
			x += accX_;
		}
		if (!collideTerrain(0, accY_))
		{
			y += accY_;
		}
	}

	public boolean collideTerrain(float accX_, float accY_)
	{
		return (collideMovingTerrain(accX_, accY_) || collideSolidTerrain(accX_, accY_)) ? true : false;
	}

	boolean collideMovingTerrain(float accX_, float accY_)
	{
		for (int i = 0; i < Game.getEntities().size(); i++)
		{
			if (Game.getEntities().get(i).mode != NOCOLLIDE)
			{
				Collision c = (Collision) Game.getEntities().get(i);

				if (c.collide(x + accX_, y + accY_, getWidth(), getHeight()) && c != this && c != Game.getPlayer1() && c != Game.getPlayer2() && c != Game.getPlayer3())
				{
					return true;
				}
			}
		}
		return false;
	}

	boolean collideSolidTerrain(float accX_, float accY_)
	{
		for (int y = (int) (this.y + accY_); y < (int) (this.y + accY_ + height); y++)
		{
			for (int x = (int) (this.x + accX_); x < (int) (this.x + accX_ + width); x++)
			{
				if (Game.getLevel().solidTiles[x / Game.TILESIZE][y / Game.TILESIZE].isSolid())
					return true;
			}
		}
		return false;
	}

	public boolean collide(float x_, float y_, float width_, float height_)
	{
		float _distX = (x + (getWidth() * 0.5f)) - (x_ + (width_ * Game.SCALE * 0.5f));
		float _distY = (y + (getHeight() * 0.5f)) - (y_ + (height_ * Game.SCALE * 0.5f));
		float _minGapX = (width_ * Game.SCALE * 0.5f) + (width_ * Game.SCALE * 0.5f);
		float _minGapY = (height_ * Game.SCALE * 0.5f) + (height_ * Game.SCALE * 0.5f);

		if (Math.abs(_distX) > _minGapX)
			return false;
		if (Math.abs(_distY) > _minGapY)
			return false;

		return true;
	}

	public boolean collide(Collision other_)
	{
		float _distX = (x + (getWidth() * 0.5f)) - (other_.getX() + (other_.getWidth() * 0.5f));
		float _distY = (y + (getHeight() * 0.5f)) - (other_.getY() + (other_.getHeight() * 0.5f));
		float _minGapX = (width * Game.SCALE * 0.5f) + (other_.getWidth() * 0.5f);
		float _minGapY = (height * Game.SCALE * 0.5f) + (other_.getHeight() * 0.5f);

		if (Math.abs(_distX) > _minGapX)
			return false;
		if (Math.abs(_distY) > _minGapY)
			return false;

		return true;
	}

}
