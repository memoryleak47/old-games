package object;

import object.Entity;
import core.Game;
import map.IceTile;
import map.WaterTile;
import map.Tile;
import static core.Game.TILESIZE;

public abstract class Mob extends Entity
{
	public static final float STEP = 0.125f; // distance per frame; has to be 1/n
	protected int direction;
	protected boolean moving;

	public Mob(int x, int y)
	{
		super(x, y);
	}

	public void tick()
	{
		walk();
	}

	public void move(int direction) // sets direction for a one-field-movement and does first step
	{
		if (moving) // if you already move, then bye
			return;

		this.direction = direction; // set direction
		if (Game.getMap().obstacleAt(getNextX(), getNextY())) // if where I want to move is an obstacle
		{
			// obstacle
			if (this instanceof Player) // and Iam a player
			{
				Game.getMap().push(getNextX(), getNextY(), direction); // push it!
			}
		}
		else // if theres no obstacle
		{
			moving = true; // move!
			walk(); // needed for first step between blocks
		}
	}

	public int getNextX() // returns x position of the tile your direction points to
	{
		if (direction == LEFT)
			return roundX()-1;
		if (direction == RIGHT)
			return roundX()+1;
		return roundX();
	}

	public int getNextY()
	{
		if (direction == UP)
			return roundY()-1;
		if (direction == DOWN)
			return roundY()+1;
		return roundY();
		
	}

	public void walk() // goes on with steps until you moved a field
	{
		if (!moving) // if not moving ... you can't walk...
			return;

		if (direction == UP) // walk
		{
			y -= STEP;
		} else if (direction == RIGHT)
		{
			x += STEP;
		} else if (direction == DOWN)
		{
			y += STEP;
		} else if (direction == LEFT)
		{
			x -= STEP;
		} else
		{
			System.out.println("awkward direction"); // DEBUG
		}

		// checks for stop moving
		if (roundX() == x && roundY() == y  // if you are on a block (not between)
				&& (!(Game.getMap().tileAt(roundX(), roundY()) instanceof IceTile) // and not on ice
				|| Game.getMap().obstacleAt(getNextX(), getNextY()))) // or on ice but infront of an obstacle
		{
			if (Game.getMap().tileAt(roundX(), roundY()) instanceof WaterTile) // if you land on a water-tile
			{
				if (this instanceof Boulder) // if you are a boulder
				{
					Game.getMap().setTile(roundX(), roundY(), Tile.waterBoulderTile); // make a waterBoulderTile out of the water
				}
				die(); // then die
			}
			moving = false;
		}
	}

	public int getDirection() { return direction; }
	public boolean isMoving() { return moving; }
}
