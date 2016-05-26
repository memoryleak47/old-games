package object;

import object.Mob;
import misc.ImageLoader;
import core.Screen;
import core.Game;

public class Player extends Mob
{
	public Player(int x, int y)
	{
		super(x, y);
	}

	public void tick()
	{
		super.tick();
		if (Game.w)
			move(UP);
		else if (Game.s)
			move(DOWN);
		else if (Game.d)
			move(RIGHT);
		else if (Game.a)
			move(LEFT);
	}

	public void render()
	{
		Screen.draw(ImageLoader.player, (int) (x * Game.TILESIZE), (int) (y * Game.TILESIZE));
	}
}
