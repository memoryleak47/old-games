package cEntity;


import base.Entity;
import main.Game;
import other.math.Vec;
import other.graphic.ImageLoader;
import other.action.KeyManager;

public class Player extends Entity
{
	private Vec vel; //high vel.y -> fly down; vel.x is just pseudo. used for getting speed for blocks
	private final float ACC = 0.2f;
	private boolean dead = false;	
	private int bananaMode = 0;
	private int saveMode = 0;

	public Player()
	{
		super(new Vec(100, 200), new Vec(Game.BLOCKSIZE, Game.BLOCKSIZE));
		vel = new Vec(Game.STANDART_SPEEDX, 0);
		image = ImageLoader.player;
	}

	public void tick()
	{
		if (KeyManager.space && bananaMode <= 0)
			vel.change(0, -ACC);
		else
			vel.change(0, ACC);

		if (bananaMode > 0)
		{
			bananaMode -= 1;
		}

		if (saveMode > 0)
		{
			saveMode -= 1;
		}

		pos.change(0, vel.getY());

		if (vel.getX() != Game.STANDART_SPEEDX)
		{
			vel.change((Game.STANDART_SPEEDX-vel.getX())/100, 0);
		}

		if (pos.getY() < Game.BORDER_HEIGHT || pos.getY() + size.getY() > Game.HEIGHT - Game.BORDER_HEIGHT)
		{
			System.out.println("Player collided border");
			die();
		}

		if (saveMode == 0)
		{
			for (int i=1; i<Game.get().getEntities().size(); i++)
			{
				if (Game.get().getEntities().get(i).collide(this))
				{
					((Block) Game.get().getEntities().get(i)).onTouchPlayer();
				}
			}
		}
	}

	protected void die()
	{
		System.out.println("Player died");
		dead = true;
	}

	public boolean isDead()
	{
		return dead;
	}

	public Vec getVel()
	{
		return vel;
	}

	public void applyBanana()
	{
		bananaMode = 40;
	}

	public void applySave()
	{
		saveMode = 100;
	}
}
