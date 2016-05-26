package cTile;

import java.awt.Graphics;

import main.Game;
import level.Tile;

public class StoneTile extends Tile {

	public StoneTile() {
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(Game.getLoader().stoneImage, x, y, Game.TILESIZE * Game.SCALE,
				Game.TILESIZE * Game.SCALE, null);
	}
}
