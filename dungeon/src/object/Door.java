package object;

import misc.ImageLoader;
import core.Screen;
import object.Mob;
import core.Game;

public class Door extends Mob
{
	public Door(int x, int y)
	{
		super(x, y);
	}

	public void render()
	{
		Screen.draw(ImageLoader.door, (int) (x * Game.TILESIZE), (int) (y * Game.TILESIZE));
	}

	public void push(int direction)
	{
		Game.nextMap();
	}

	public boolean isObstacle() { return true; } // needed for calling push()
}
