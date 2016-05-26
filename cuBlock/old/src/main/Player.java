package main;

import helps.Mathematics;

import java.awt.image.BufferedImage;

import base.Animation;
import base.CharacterController;

public class Player extends CharacterController
{
	private static float SPEED = 1;
	int				runnerCounter	= 0;
	public int		number, score = 0;
	public boolean	up, lt, rt;
	public boolean	runner			= false;
	public float	startX;
	public float	startY;

	protected Player(int x_, int y_, int number_)
	{
		super(x_, y_);
		startX = x_;
		startY = y_;
		width = 10;
		height = 10;

		number = number_;
		switch (number_)
		{
			case 1:
			{
				runner = true;
				image = ImageLoader.player1;
				break;
			}
			case 2:
			{
				image = ImageLoader.player2;
				break;
			}
			case 3:
			{
				image = ImageLoader.player3;
				break;
			}
		}
	}

	public void tick()
	{
		collideOthers();
		checkSqueezing();

		if (runner)
		{
			Game.getEntities().add(new Animation(this, ImageLoader.runnerParticle, 5, 5, 10));
			checkCatch();
			runnerCounter++;
			if (runnerCounter > 1000)
			{
				score++;
				runnerCounter = 0;
			}
		}

		super.tick();

		if (inWater)
		{
			applyDrag();
			if (up)
				accY += 5 * -Mathematics.toOne(GRAVITY);
			applyDrag();
			applyDrag();
		} else
		{
			if (!up && (Mathematics.toOne(accY)) != (Mathematics.toOne(GRAVITY)))
			{
				accY = 0;
			}
			if (isGrounded && up)
			{
				accY = (Mathematics.toOne(GRAVITY)) * -13;
				if (collideTerrain(0, accY)) // for jump
					accY = (Mathematics.toOne(GRAVITY)) * -3;

			}
		}

		if (lt)
			accX -= SPEED;
		if (rt)
			accX += SPEED;

		move(accX * speed, accY * speed);
	}

	void checkCatch()
	{
		if (number != 1 && collide(Game.getPlayer1()))
		{
			die(0);
			Game.getPlayer1().runner = true;
			Game.getPlayer1().score += 5;
			runner = false;
		}
		if (number != 2 && collide(Game.getPlayer2()))
		{
			die(0);
			Game.getPlayer2().runner = true;
			Game.getPlayer2().score += 5;
			runner = false;
		}
		if (number != 3 && collide(Game.getPlayer3()))
		{
			die(0);
			Game.getPlayer3().runner = true;
			Game.getPlayer3().score += 5;
			runner = false;
		}
	}

	void checkSqueezing()
	{
		float _hWidth = width;
		float _hHeight = height;
		if (collideTerrain(-_hWidth, _hHeight) && collideTerrain(_hWidth, _hHeight) && collideTerrain(_hWidth, -_hHeight) && collideTerrain(-_hWidth, -_hHeight) && collideTerrain(0, _hHeight) && collideTerrain(0, -_hHeight) && collideTerrain(_hWidth, 0) && collideTerrain(-_hWidth, 0) && collideTerrain(0, 0))
		{
			die(2);
		}
	}

}
