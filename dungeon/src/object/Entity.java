package object;

import core.Game;
import object.Player;

public abstract class Entity
{
	public static final int UP = 1, RIGHT = 2, DOWN = 3, LEFT = 4;
	private boolean alive = true;
	float x, y;

	public Entity(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void die()
	{
		if (this instanceof Player)
			Game.restartMap();
		else
			alive = false;
	}

	public boolean isAlive() { return alive; }

	public void push(int direction) {} // executed when player runs against this

	public abstract void tick();
	public abstract void render();
	public boolean isObstacle() { return false; }
	public int roundX() { return (int) x; } // returns field in TILESIZE
	public int roundY() { return (int) y; }
	public float x() { return x; } // returns real position in TILESIZE
	public float y() { return y; }
}
