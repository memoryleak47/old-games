package cTile;

import java.awt.Graphics;

import main.Game;
import level.Tile;

public class FireTile extends Tile {

	public FireTile() {
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(Game.getLoader().fireImage, x, y, Game.TILESIZE * Game.SCALE,
				Game.TILESIZE * Game.SCALE, null);
	}
	
	public boolean doesDamage()
	{
		return true;
	}
	
	public boolean isSolid()
	{
		return false;
	}

}


