package Missiles;

import java.awt.Graphics;

import BaseClasses.Missile;
import Important.Game;
import Unimportant.ImageLoader;

public class MageMissile extends Missile {
	
	int exploTime = 20;
	int exploCounter;
	
	public MageMissile(float x, float y) {
		super(x, y, 0, 0, 20);
		playerIsAim = false;
		maxRange = 1000;
		damage = 30;
		exploCounter = 0;
		
	}

	public void render(Graphics g) {
		Game.getLoader();
		g.drawImage(ImageLoader.mageMissileImage, (int) x, (int) y, size * Game.SCALE, size * Game.SCALE, null);
	}
	
	public void tick()
	{
		super.checkDead();
	exploCounter++;
		if(exploTime <= exploCounter)
		{
			playerIsAim = true;
		}
		
		if(exploCounter > exploTime+20)
		{
			dead = true;
		}
		
	}
}
