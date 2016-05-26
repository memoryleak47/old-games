package cTile;

import java.awt.Graphics;

import main.Game;
import level.Tile;

public class SaveTile extends Tile {

	public SaveTile() {
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(Game.getLoader().saveImage, x, y, Game.TILESIZE * Game.SCALE,
				Game.TILESIZE * Game.SCALE, null);
	}
	
	public boolean isSave()
	{
		return true;
	}

}
