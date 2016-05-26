package base;

import java.awt.image.BufferedImage;
import java.lang.Math;

import other.math.Vec;
import main.Game;

public abstract class Entity //basically a Rectangle class
{
	protected BufferedImage image;
	protected Vec pos;
	protected Vec size;

	public Entity(Vec pos, Vec size)
	{
		this.pos = new Vec(pos);
		this.size = new Vec(size);
	}

	public boolean collide(Entity o)
	{
		return     (Math.abs(pos.getX() - o.getPos().getX()) < Game.BLOCKSIZE)
			&& (Math.abs(pos.getY() - o.getPos().getY()) < Game.BLOCKSIZE); //TODO size (Game.BLOCKSIZE) is unsure.
	}

	protected void die()
	{
		Game.get().getEntities().remove(this);
	}

	public Vec getPos()
	{
		return pos;
	}

	public Vec getSize()
	{
		return size;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public abstract void tick();

}
