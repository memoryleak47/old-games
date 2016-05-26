package level;

import java.awt.Graphics;

import cTile.FireTile;
import cTile.GrassTile;
import cTile.SaveTile;
import cTile.StoneTile;
import cTile.VoidTile;
import cTile.WinTile;

public abstract class Tile {

	static public GrassTile grassTile = new GrassTile();
	static public VoidTile voidTile = new VoidTile();
	static public StoneTile stoneTile = new StoneTile();
	static public FireTile fireTile = new FireTile();
	static public WinTile winTile = new WinTile();
	static public SaveTile saveTile = new SaveTile();

	public Tile() {
	}

	public boolean doesDamage() {
		return false;
	}

	public boolean isSolid() {
		return true;
	}

	public boolean isWin() {
		return false;
	}
	
	public boolean isSave() {
		return false;
	}

	public abstract void render(Graphics g, int x, int y);

	// public abstract void tick();

}
