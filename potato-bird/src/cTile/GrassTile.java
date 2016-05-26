package cTile;

import java.awt.Graphics;

import main.Game;
import level.Tile;

public class GrassTile extends Tile {

	public GrassTile() {
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(Game.getLoader().grassImage, x, y, Game.TILESIZE * Game.SCALE,
				Game.TILESIZE * Game.SCALE, null);
	}

}
