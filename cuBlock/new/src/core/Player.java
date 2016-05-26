package core;

import misc.Mathematics;
import base.Animation;
import base.CharacterController;

import java.awt.image.BufferedImage;
import static java.lang.Math.hypot;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Player extends CharacterController
{
	private static float ACC = 1;
	private static float JUMP_POWER = 20;
	private static float SIZE = 10;
	private static float NEAR_RANGE = 40;
	private static float RUNNER_POINT_TIME = 20;

	int runnerCounter = 0;
	public int number, score = 0;
	public boolean up, lt, rt;
	public boolean runner = false;
	public float startX;
	public float startY;

	protected Player(int x_, int y_, int number_)
	{
		super(x_, y_);
		startX = x_;
		startY = y_;
		width = SIZE;
		height = SIZE;

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

	public int amountOfCloseCatchers()
	{
		int amount = 0;
		if (number != 1 &&
			hypot(	getX() - Game.getPlayer1().getX() + (getX() < Game.getPlayer1().getX()? SIZE : 0),
				getY() - Game.getPlayer1().getY() + (getY() < Game.getPlayer1().getY()? SIZE : 0)) < NEAR_RANGE) //TODO only leftTop is colliding
			amount++;

                if (number != 2 &&
                        hypot(  getX() - Game.getPlayer2().getX() + (getX() < Game.getPlayer2().getX()? SIZE : 0),
                                getY() - Game.getPlayer2().getY() + (getY() < Game.getPlayer2().getY()? SIZE : 0)) < NEAR_RANGE) //TODO as above
                        amount++;

                if (number != 3 &&
                        hypot(  getX() - Game.getPlayer3().getX() + (getX() < Game.getPlayer3().getX()? SIZE : 0),
                                getY() - Game.getPlayer3().getY() + (getY() < Game.getPlayer3().getY()? SIZE : 0)) < NEAR_RANGE) //TODO as above
                        amount++;



		return amount;
	}

	public void tick()
	{
		collideOthers();
		checkSqueezing();

		if (runner)
		{
			checkCatch();
		
			runnerCounter += amountOfCloseCatchers();
			if (runnerCounter > RUNNER_POINT_TIME)
			{
				score++;
				runnerCounter = 0;
			}
		}

		super.tick();

		if (!Game.getLevel().inBounds(x, y))
			die(2);

		if (inWater)
		{
			applyDrag();
			if (up)
				accY += JUMP_POWER * -Mathematics.toOne(GRAVITY);
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
				accY = -(Mathematics.toOne(GRAVITY)) * JUMP_POWER;
			}
		}

		if (lt)
			accX -= ACC;
		if (rt)
			accX += ACC;

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
		if (collideTerrain(-_hWidth, _hHeight)
			&& collideTerrain(_hWidth, _hHeight)
			&& collideTerrain(_hWidth, -_hHeight)
			&& collideTerrain(-_hWidth, -_hHeight)
			&& collideTerrain(0, _hHeight)
			&& collideTerrain(0, -_hHeight)
			&& collideTerrain(_hWidth, 0)
			&& collideTerrain(-_hWidth, 0)
			&& collideTerrain(0, 0))
		{
			die(2);
		}
	}

	public void render(Graphics g)
	{
		if (!inCamera())
			return;
		
		super.render(g);

		if (runner)
		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(number==1?1:0, number==2?1:0, number==3?1:0, 0.5f));
			g2.fillOval((int) (x-(NEAR_RANGE*2-SIZE)/2), (int) (y-(NEAR_RANGE*2-SIZE)/2), (int) (NEAR_RANGE*2), (int) (NEAR_RANGE*2));
		}
	}

}
