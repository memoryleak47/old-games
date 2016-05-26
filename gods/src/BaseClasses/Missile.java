package BaseClasses;

import java.awt.Graphics;

import Important.Game;

public abstract class Missile {

	protected float x;
	protected float y;
	protected float direcX;
	protected float direcY;
	protected int size;
	protected boolean dead = false;
	public boolean playerIsAim;
	protected int range, maxRange, damage;

	public Missile(float x, float y, float direcX, float direcY, int size) {
		this.x = x;
		this.y = y;
		this.direcX = direcX;
		this.direcY = direcY;
		this.size = size;
		
		this.x -= size;
		this.y -= size;

		playerIsAim = true;
		range = 0;
		maxRange = 100;
		damage = 10;

		float value = Math.abs(direcX) + Math.abs(direcY);

		if(direcX != 0)
		this.direcX /= value / 10;
		if(direcY != 0)
		this.direcY /= value / 10;
	}
	
	public Missile(float x, float y, float direcX, float direcY) {
		this.x = x;
		this.y = y;
		this.direcX = direcX;
		this.direcY = direcY;
		size = 5;
		
		x -= size;
		y -= size;

		playerIsAim = true;
		range = 0;
		maxRange = 100;
		damage = 10;
		
		float value = Math.abs(direcX) + Math.abs(direcY);
		
		if(direcX != 0)
		this.direcX /= value / 10;
		if(direcY != 0)
		this.direcY /= value / 10;
	}
	

	public void tick() {
		range += Math.max(Math.abs(direcX), Math.abs(direcY));
		x += direcX;
		y += direcY;
		checkDead();
	}

	public void checkDead() {
		if (range >= maxRange)
			dead = true;
		if (x > Game.WIDTH * Game.SCALE || x < 0
				|| y > Game.HEIGHT * Game.SCALE || y < 0)
			dead = true;
	}

	public abstract void render(Graphics g);

	public boolean isDead() {
		return dead;
	}

	public void setDead() {
		dead = true;
		Game.getSplatters().add(new Splatter(x + direcX, y + direcY));
	}

	public float getX() {
		return x + size;
	}

	public float getY() {
		return y + size;
	}

	public int getSize() {
		return size;
	}

	public int getDamage() {
		return damage;
	}
}
