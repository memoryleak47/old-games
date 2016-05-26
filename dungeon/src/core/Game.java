package core;

import java.util.LinkedList;

import core.Screen;
import object.Entity;
import map.MapManager;
import map.Map;
import object.Player;

public class Game
{
	public static boolean w = false, a = false, s = false, d = false;
	public static final int TILESIZE = 32, SW = 800, SH = 600;
	private static Game instance = new Game();
	private static MapManager mapMan;
	private static boolean nextMap = false;

	public static void main(String args[])
	{
		get().run();
	}

	private void run()
	{
		// init
		mapMan = new MapManager();
		// game loop
		long lastTime = System.nanoTime();
		final double ns = 1000000000 / 60D;
		double delta = 0;
		while (true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				render();
				delta--;
			}
		}
	}

	public static void nextMap()
	{
		nextMap = true;
	}

	public static void restartMap() // called when player dies or presses r
	{
		mapMan.restartMap();
	}

	private void tick()
	{
		Screen.tick(); // checks cam pos ... n stuff
		if (nextMap) // if next map should be loaded, don't tick. load map
		{
			mapMan.nextMap();
			nextMap = false;
		} // else ...

		for (int i = 0; i < getMap().entities.size(); i++) // for all entities
		{
			if (!getMap().entities.get(i).isAlive()) // if its dead
			{
				getMap().entities.remove(getMap().entities.get(i)); // remove it
				i--; // count back for not excluding someone
			} else
			{
				getMap().entities.get(i).tick(); // if alive: tick
			}
		}
	}

	private void render()
	{
		getMap().render(); // render map
		for (Entity entity : getMap().entities) // render entities
		{
			entity.render();
		}

		Screen.update();
	}

	public static Map getMap() { return mapMan.getMap(); }
	public static Game get() { return instance; }
	public static Player getPlayer() { return getMap().getPlayer(); }
}
