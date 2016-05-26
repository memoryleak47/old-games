package cEntity;


import base.Entity;
import other.math.Vec;
import main.Game;

public abstract class Block extends Entity
{
	public abstract void onTouchPlayer();

	public Block(Vec pos, Vec size)
	{
		super(pos, size);
	}

	public void tick()
	{
		pos.change(-Game.get().getPlayer().getVel().getX(), 0);

		if (pos.getX() < 0)
			die();
	}	
}
