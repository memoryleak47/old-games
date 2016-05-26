package cTile;

import java.awt.Graphics;

import main.Game;
import level.Tile;

public class WinTile extends Tile {

	public WinTile() {
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(Game.getLoader().winImage, x, y, Game.TILESIZE * Game.SCALE,
				Game.TILESIZE * Game.SCALE, null);
	}

	public boolean isSolid()
	{
		return false;
	}
	
	public boolean isWin()
	{
		return true;
	}

}


