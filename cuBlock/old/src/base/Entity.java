package base;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public abstract class Entity
{
	public int				mode		= 0;
	public static final int	NORMAL		= 0;
	public static final int	NOCOLLIDE	= -1;
	public static float		GRAVITY		= 0.60f;
	public static int		cameraOffsetX;
	public static int		cameraOffsetY;

	public float			accX, accY;

	protected BufferedImage	image;
	protected float			x, y, width, height;

	public void render(Graphics g_)
	{
		if (!inCamera())
			return;

		g_.drawImage(image, (int) (x - Entity.getCameraOffsetX()), (int) (y - Entity.getCameraOffsetY()), (int) (getWidth()), (int) (getHeight()), null);
	}

	public float getCenterX()
	{
		return x + getWidth() * 0.5f;
	}

	public float getCenterY()
	{
		return y + getHeight() * 0.5f;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public float getWidth()
	{
		return width * Game.SCALE;
	}

	public float getHeight()
	{
		return height * Game.SCALE;
	}

	public void move(float accX_, float accY_)
	{
		x += accX_;
		y += accY_;
	}

	public void tick()
	{
		float _x = getCenterX() / Game.TILESIZE;
		float _y = getCenterY() / Game.TILESIZE;
		float _levelSizeX = Game.getLevel().getWidth() * Game.SCALE;
		float _levelSizeY = Game.getLevel().getHeight() * Game.SCALE;

		if (_x > _levelSizeX || _x < 0 || _y > _levelSizeY || _y < 0)
			Game.getEntities().remove(this);
	}

	public static int getCameraOffsetX()
	{
		return cameraOffsetX;
	}

	public static int getCameraOffsetY()
	{
		return cameraOffsetY;
	}

	public void setPosition(Entity other)
	{
		x = (int) other.getCenterX();
		y = (int) other.getCenterY();
		x -= this.getWidth() * 0.5f;
		y -= this.getHeight() * 0.5f;
	}

	public boolean inCamera()
	{

		float _distX = (this.getWidth() * 0.5f) + (Game.WIDTH * Game.DISPLAYSCALE);
		float _distY = (this.getHeight() * 0.5f) + (Game.HEIGHT * Game.DISPLAYSCALE);

		if (Math.abs((width * Game.SCALE * 0.5f) - (x + getWidth() * 0.5f)) > _distX)
			return false;
		if (Math.abs((height * Game.SCALE * 0.5f) - (y + getHeight() * 0.5f)) > _distY)
			return false;

		return true;
	}

	public void applyGravity(float strength_)
	{
		accY += (GRAVITY * strength_);
	}

	public void applyDrag()
	{
		accY -= accY / 15;
		accX -= accX / 3;
	}
}
