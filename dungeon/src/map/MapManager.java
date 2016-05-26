package map;

import misc.ImageLoader;
import map.Map;

public class MapManager
{
	int mapCounter = 1;
	Map map;

	public MapManager()
	{
		map = load();
	}

	public void nextMap()
	{
		mapCounter++;
		if (/*NOMORE*/ true) // TODO
			map = load();
		else
			System.out.println("no more maps"); // DEBUG
	}

	public void restartMap()
	{
		map = load();
	}

	private Map load()
	{
		return new Map("/map/" + mapCounter + ".png");
	}

	public Map getMap() { return map; }
}
