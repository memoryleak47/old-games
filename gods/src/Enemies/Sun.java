package Enemies;

import BaseClasses.Enemy;
import Important.Game;
import Missiles.SunMissile;
import Unimportant.ImageLoader;

public class Sun extends Enemy {
	private int direcCounter = 0;

	public Sun() {
		super();
		size = 20;
		Game.getLoader();
		image = ImageLoader.sunImage;
		shootingTime = 30;
	}

	public void tick() {
		super.tick();
		
	}

	public void shoot() {
		int turn = 2;
		x += x > Game.getPlayer().getX() ? -3 : 3;
		y += y > Game.getPlayer().getY() ? -3 : 3;

		switch (direcCounter) {
		case 0: {
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS, -BS));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS, -BS));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS, BS));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS, BS));
			break;
		}
		case 1: {
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS + turn, -BS
							- turn));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS + turn, -BS
							+ turn));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS - turn, BS
							- turn));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS - turn, BS
							+ turn));
			break;
		}
		case 2: {
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, 0, -BS));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS, 0));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, 0, BS));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS, 0));
			break;
		}
		case 3: {
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS - turn, -BS
							+ turn));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS - turn, -BS
							- turn));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, -BS + turn, BS
							+ turn));
			Game.getMissiles().add(
					new SunMissile(x + size / 2, y + size / 2, BS + turn, BS
							- turn));
			break;
		}
		}

		direcCounter++;
		if (direcCounter > 3)
			direcCounter = 0;
	}
}
