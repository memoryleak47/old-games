package Missiles;

import java.awt.Graphics;

import BaseClasses.Missile;
import Important.Game;
import Unimportant.ImageLoader;

public class PlayerMissile extends Missile{

	public PlayerMissile(float x, float y, float direcX, float direcY) {
		super(x, y, direcX, direcY);
		playerIsAim = false;
		maxRange = 100;
	}

	public void render(Graphics g) {
		
		Game.getLoader();
		g.drawImage(ImageLoader.playerMissileImage, (int) x, (int) y, size * Game.SCALE, size * Game.SCALE, null);
	}
	
	public void tick()
	{
		super.tick();
	}

	public void collide() {
		//SUN COLLIDE
		
	}

}
