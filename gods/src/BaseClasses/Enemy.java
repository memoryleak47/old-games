package BaseClasses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Important.Game;
import Unimportant.ImageLoader;

public abstract class Enemy {
	protected int health;
	protected int maxHealth;
	protected int x, y;
	protected int counter = 0;
	protected float shootingTime;
	public boolean dead;
	protected final int BS = 5;

	protected int size;
	protected BufferedImage image;

	public Enemy() {
		dead = false;
		x = (int) (Math.random() * Game.WIDTH);
		y = (int) (Math.random() * Game.HEIGHT);
		maxHealth = 50;
		health = maxHealth;
		shootingTime = 10;
	}

	public void tick() {
		collide();
		if (health <= 0)
			dead = true;
		
		counter++;
		if (counter >= shootingTime)
		{
			shoot();
		counter = 0;
		}
	}

	public void render(Graphics g) {
		Game.getLoader();
		g.drawImage(image, x, y, size * Game.SCALE, size * Game.SCALE, null);

		Game.getLoader();
		g.drawImage(ImageLoader.healthBarImage, x, y - 20, 2 * size * health
				/ maxHealth, 10, null);
	}

	public void collide() {

		LinkedList<Missile> missiles = Game.getMissiles();

		for (int i = 0; i < missiles.size(); i++) {

			Missile m = missiles.get(i);

			if (!m.playerIsAim) {

				float a = (m.getX() - getX());
				float b = (m.getY() - getY());
				float dist = (float) Math.sqrt(a * a + b * b);

				float sizes = (m.getSize() + size); //?

				if (dist <= sizes) {

					health -= m.getDamage();
					m.setDead();
				}
			}
		}
	}

	private float getY() {
		return y + size;
	}

	private float getX() {
		return x + size;
	}

	public boolean isDead() {
		return dead;
	}
	
	public abstract void shoot();

}
