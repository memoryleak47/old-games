package Important;

import java.awt.Graphics;
import java.util.LinkedList;

import Unimportant.ImageLoader;

import BaseClasses.Missile;

public class Player {

	static int health;
	static int maxHealth;
	static int size;
	static int x, y;
	public boolean dead;

	public static boolean left, right, up, down, space;
	static private int shotReady;
	private final int SPEED = 3;

	Player() {
		dead = false;
		size = 10;
		maxHealth = 100;
		health = maxHealth;
		x = 400;
		y = 400;
		shotReady = 0;
	}

	void tick() {
		collide();
		input();

		if (shotReady > 0)
			shotReady--;
		if (health <= 0)
			dead = true;
	}

	void input() {
		if (left && x > 0) {
			x -= SPEED;
		}
		if (right && x < (Game.WIDTH * Game.SCALE - 27)) {
			x += SPEED;
		}
		if (up && y > 0) {
			y -= SPEED;
		}
		if (down && y < (Game.HEIGHT * Game.SCALE - 50)) {
			y += SPEED;
		}
		if (space && shotReady <= 0) {
			shoot();
			shotReady = 10;
		}
	}

	void shoot() {
		Game.addPlayerMissile();
	}

	static public void render(Graphics g) {
		Game.getLoader();
		g.drawImage(ImageLoader.playerImage, x, y, size * Game.SCALE, size
				* Game.SCALE, null);

		Game.getLoader();
		g.drawImage(ImageLoader.healthBarImage, x, y - 5, 2 * size * health
				/ maxHealth, 2, null);
	}

	public float getX() {
		return x + size;
	}

	public float getY() {
		return y + size;
	}

	public void collide() {

		LinkedList<Missile> missiles = Game.getMissiles();

		for (int i = 0; i < missiles.size(); i++) {

			Missile m = missiles.get(i);

			if (m.playerIsAim) {

				float a = (m.getX() - getX());
				float b = (m.getY() - getY());
				float dist = (float) Math.sqrt(a * a + b * b);

				float sizes = (m.getSize() + size);

				if (dist <= sizes) {

					health -= m.getDamage();
					m.setDead();
				}
			}
		}
	}

	public boolean isDead() {
		return dead;
	}

	public int DX() {
		int RIGHT = right? 1:-1;
		int LEFT = left? 1:-1;
		return RIGHT-LEFT;
	}

	public int DY() {
		int UP = up? 1:-1;
		int DOWN = down? 1:-1;
return DOWN-UP;
	}
}
