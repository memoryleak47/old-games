package Enemies;

import BaseClasses.Enemy;
import Important.Game;
import Missiles.GhostMissile;
import Unimportant.ImageLoader;

public class Ghost extends Enemy {

	public Ghost() {
		super();
		size = 20;
		Game.getLoader();
		image = ImageLoader.ghostImage;
		shootingTime = 50;
	}

	public void tick() {
		super.tick();
	}

	public void shoot() {
		float Xdif = (Game.getPlayer().getX() - x) * BS;
		float Ydif = (Game.getPlayer().getY() - y) * BS;

		Game.getMissiles().add(
				new GhostMissile(x + size / 2, y + size / 2, Xdif, Ydif));

	}

}
