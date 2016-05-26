package Missiles;

import java.awt.Graphics;

import BaseClasses.Missile;
import Important.Game;
import Unimportant.ImageLoader;

public class GhostMissile extends Missile {

	public GhostMissile(float x, float y, float direcX, float direcY) {
		super(x, y, direcX, direcY, 30);
		this.direcX *= 0.5f;
		this.direcY *= 0.5f;
		maxRange = 10000;
		damage = 30;
		
	}

	public void render(Graphics g) {
		
		Game.getLoader();
		g.drawImage(ImageLoader.ghostMissileImage, (int) x, (int) y, size * Game.SCALE, size * Game.SCALE, null);
	}
}
