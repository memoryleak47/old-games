package tiles;
import tileMain.Tile;

public class RockTile extends Tile {

	public RockTile() {
		super("rock");
	}

	public void tick() {

	}
	public boolean isSolid()
	{
	return true;	
	}

}
