package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	private int x, y, xo, yo, xs, counter, anim, returnX, returnY;
	private BufferedImage image;
	private final int SPEED = 6, ySPEED = 2, PLAYERWIDTH = 15,
			PLAYERHEIGHT = 15, STAND = 0, GO = 1, FLY = 2;
	private float veloY;
	public boolean right = false, left = false, up = false, down = false,
			jump = false, shoot = false, lookRight;

	Player(int x, int y, BufferedImage image) {
		this.x = x;
		this.y = y;
		this.image = image;
		xo = 0;
		yo = 0;
		xs = 0;
		veloY = 0;
		counter = 0;
		anim = 0;
		lookRight = true;

	}

	public void tick() {
		animate();
		input();
		calc();
		move();
	}

	public void input() {
		
	
		xs = 0;
		if (up) {
			veloY -= 0.25f;
		}
		if (jump && touchingBot()){
			veloY -= 11;
		}
		if (right) {
			xs += SPEED;
		}
		if (left) {
			xs -= SPEED;
		}

	}

	public void calc() {

		veloY += (!down) ? 0.2f * ySPEED : 0.6f * ySPEED; // gravity

		if (veloY > 12)
			veloY = 12;
		if (veloY < -12)
			veloY = -12;

		if (xs != 0)
			lookRight = (xs > 0) ? true : false;

		if (touchingTop()) // PREVENT STICKING
		{
			veloY = Math.max(veloY, 0);
		}
		if (touchingBot()) // PREVENT STICKING
		{
			veloY = Math.min(veloY, 0);
		}

	}

	public void move() {

		if (!collision(0, (int) veloY)) {
			yo += (int) veloY;
		} else if (!collision(0, 1)) {
			yo += 1;
		} else if (!collision(0, -1)) {
			yo -= 1;
		}

		if (!collision(xs, 0)) // COLLISION
		{
			xo += xs;
		}
	}

	private boolean collision(int xs, int ys) {
		if (winnyCollision(xs, ys)) {
			Game.getLevel().nextLevel();
			returnX = 0;
			returnY = 0;
			die();
			return false;
		}
		
		if(savyCollision(xs, ys)) {
			returnX = xo;
			returnY = yo;
		}
		if(deadlyCollision(xs, ys))
		{
			die();
			return false;
		}

		if (ys != 0)
			ys = (ys > 0) ? 6 : -6; // 6

		if (Game.getLevel()
				.getTile((xo + xs + x) / (Game.TILESIZE * Game.SCALE),
						(yo + ys + y) / (Game.TILESIZE * Game.SCALE)).isSolid())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x + PLAYERWIDTH * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y) / (Game.TILESIZE * Game.SCALE)).isSolid())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x) / (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + PLAYERHEIGHT * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE)).isSolid())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x + PLAYERWIDTH * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + PLAYERHEIGHT * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE)).isSolid())
			return true;

		return false;
	}

	public boolean deadlyCollision(int xs, int ys) {
			if (ys != 0)
				ys = (ys > 0) ? 6 : -6; // 6

			if (Game.getLevel()
					// TOP
					.getTile(
							(xo + xs + x + (PLAYERWIDTH * Game.SCALE) / 2 - 1)
									/ (Game.TILESIZE * Game.SCALE),
							(yo + ys + y) / (Game.TILESIZE * Game.SCALE))
					.doesDamage())
				return true;

			if (Game.getLevel()
					// LEFT
					.getTile(
							(xo + xs + x) / (Game.TILESIZE * Game.SCALE),
							(yo + ys + y + (PLAYERHEIGHT * Game.SCALE) / 2 - 1)
									/ (Game.TILESIZE * Game.SCALE)).doesDamage())
				return true;

			if (Game.getLevel()
					// RIGHT
					.getTile(
							(xo + xs + x + PLAYERWIDTH * Game.SCALE - 1)
									/ (Game.TILESIZE * Game.SCALE),
							(yo + ys + y + (PLAYERHEIGHT * Game.SCALE) / 2 - 1)
									/ (Game.TILESIZE * Game.SCALE)).doesDamage())
				return true;

			if (Game.getLevel()
					.getTile(
							(xo + xs + x + (PLAYERWIDTH * Game.SCALE) / 2 - 1)
									/ (Game.TILESIZE * Game.SCALE),
							(yo + ys + y + PLAYERHEIGHT * Game.SCALE - 1)
									/ (Game.TILESIZE * Game.SCALE)).doesDamage())
				return true;

			return false;
		}

	public boolean winnyCollision(int xs, int ys) {
		if (ys != 0)
			ys = (ys > 0) ? 6 : -6; // 6

		if (Game.getLevel()
				.getTile((xo + xs + x) / (Game.TILESIZE * Game.SCALE),
						(yo + ys + y) / (Game.TILESIZE * Game.SCALE)).isWin())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x + PLAYERWIDTH * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y) / (Game.TILESIZE * Game.SCALE)).isWin())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x) / (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + PLAYERHEIGHT * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE)).isWin())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x + PLAYERWIDTH * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + PLAYERHEIGHT * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE)).isWin())
			return true;

		return false;
	
	}
	
	public boolean savyCollision(int xs, int ys) {
		if (deadlyCollision(0, 0))
			return false;
		if (ys != 0)
			ys = (ys > 0) ? 6 : -6; // 6

		if (Game.getLevel()
				// TOP
				.getTile(
						(xo + xs + x + (PLAYERWIDTH * Game.SCALE) / 2 - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y) / (Game.TILESIZE * Game.SCALE))
				.isSave())
			return true;

		if (Game.getLevel()
				// LEFT
				.getTile(
						(xo + xs + x) / (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + (PLAYERHEIGHT * Game.SCALE) / 2 - 1)
								/ (Game.TILESIZE * Game.SCALE)).isSave())
			return true;

		if (Game.getLevel()
				// RIGHT
				.getTile(
						(xo + xs + x + PLAYERWIDTH * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + (PLAYERHEIGHT * Game.SCALE) / 2 - 1)
								/ (Game.TILESIZE * Game.SCALE)).isSave())
			return true;

		if (Game.getLevel()
				.getTile(
						(xo + xs + x + (PLAYERWIDTH * Game.SCALE) / 2 - 1)
								/ (Game.TILESIZE * Game.SCALE),
						(yo + ys + y + PLAYERHEIGHT * Game.SCALE - 1)
								/ (Game.TILESIZE * Game.SCALE)).isSave())
			return true;

		return false;
	}

	
	public void render(Graphics g) {
		int pw = (PLAYERWIDTH * Game.SCALE);
		int ph = (PLAYERHEIGHT * Game.SCALE);

		if (lookRight) {
			g.drawImage(image, x, y, pw, ph, null);
		} else {
			g.drawImage(image, x + pw, y, -pw, ph, null); // MY METHOD,
		}

	}

	public int getYo() {
		return yo;
	}

	public void setYo(int yo) {
		this.yo = yo;
	}

	public int getXo() {
		return xo;
	}

	public void setXo(int xo) {
		this.xo = xo;
	}

	void animate() {
		if (!touchingBot()) {
			if (up) {
				anim = FLY;
				changeAnim("fly");
				return;
			} else {
				anim = STAND;
				changeAnim("stand");
				return;
			}
		} else {

			if (anim == FLY) {
				anim = STAND;
				counter = 0;
				changeAnim("stand");
			}

			if (left || right) {
				counter++;
				if (counter > 10) {
					counter = 0;
					switch (anim) {
					case STAND: {
						changeAnim("go");
						anim = GO;
						break;
					}
					default: {
						changeAnim("stand");
						anim = STAND;
						break;
					}
					}
				} // COUNTER > 10
			} // LEFT|| RIGHT
		}
	}

	void changeAnim(String pic) {
		switch (pic) {
		case "stand": {
			image = Game.getLoader().playerStand;
			break;
		}
		case "go": {
			image = Game.getLoader().playerGo;
			break;
		}
		case "fly": {
			image = Game.getLoader().playerFly;
			break;
		}
		default: {
			System.out.println("wrong string  changeAnim ~ Player");
			break;
		}
		}
	}

	public boolean touchingBot() {
		return collision(0, SPEED);
	}

	public boolean touchingTop() {
		return collision(0, -SPEED);
	}

	public void die() {
		xo = returnX;
		yo = returnY;
		veloY = 0;
	}

}
