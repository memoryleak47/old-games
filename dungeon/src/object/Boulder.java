package object;

import object.Mob;
import misc.ImageLoader;
import core.Screen;
import core.Game;
import map.IceTile;
import map.WaterTile;

public class Boulder extends Mob
{
	public Boulder(int x, int y)
	{
		super(x, y);
	}

	public void render()
	{
		Screen.draw(ImageLoader.boulder, (int) (x * Game.TILESIZE), (int) (y * Game.TILESIZE));
	}

	public void push(int direction)
	{
		move(direction);
	}

	public boolean isObstacle()
	{
		if (!isAlive()) // if not alive
			return false; // not an obstacle
			
		return !( // if boulder is 
			(Game.getMap().tileAt(roundX(), roundY()) instanceof IceTile // on ice
			|| Game.getMap().tileAt(roundX(), roundY()) instanceof WaterTile) // or on water
			&& moving); // and moving -> no obstacle, otherwise it is
	}
}
