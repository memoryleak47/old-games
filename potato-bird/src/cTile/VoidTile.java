package cTile;

import java.awt.Graphics;

import main.Game;
import level.Tile;

public class VoidTile extends Tile {

	public VoidTile() {
	}

	public boolean isSolid() {
		return false;
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(Game.getLoader().voidImage, x, y, Game.TILESIZE * Game.SCALE,
				Game.TILESIZE * Game.SCALE, null);
	}

	public void tick() {
		// TODO Auto-generated method stub

	}
}
