package Enemies;

import BaseClasses.Enemy;
import Important.Game;
import Missiles.MageMissile;
import Unimportant.ImageLoader;

public class Mage extends Enemy {

	public Mage() {
		super();
		size = 20;
		Game.getLoader();
		image = ImageLoader.mageImage;
		shootingTime = 30;
	}

	public void tick() {
		super.tick();
	}

	public void shoot() {
		float X = Game.getPlayer().getX() + Game.getPlayer().DX() * 30;
		float Y = Game.getPlayer().getY() + Game.getPlayer().DY() * 30;

		Game.getMissiles().add(new MageMissile(X, Y));

	}

}
