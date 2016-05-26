package Missiles;

import java.awt.Graphics;

import BaseClasses.Missile;
import Important.Game;
import Unimportant.ImageLoader;

public class SunMissile extends Missile {

	public SunMissile(float x, float y, float direcX, float direcY) {
		super(x, y, direcX, direcY);
		damage = 30;
		maxRange = 200;
	}

	public void render(Graphics g) {
		
		Game.getLoader();
		g.drawImage(ImageLoader.sunMissileImage, (int) x, (int) y, size * Game.SCALE, size * Game.SCALE, null);
	}
}
