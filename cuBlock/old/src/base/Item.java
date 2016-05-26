package base;

import main.Game;
import main.Player;

public abstract class Item extends Collision
{
	public Item(float x_, float y_, float width_, float height_)
	{
		Game.itemCounter++;
		mode = NOCOLLIDE;
		x = x_;
		y = y_;
		width = width_;
		height = height_;
		if (collideTerrain(0, 0))
			Game.getEntities().remove(this);

	}

	public void tick()
	{
		super.tick();
		if (Game.getPlayer1().collide(x, y, width, height))
		{
			sendTo(Game.getPlayer1());
			Game.getEntities().remove(this);
			Game.itemCounter--;
		}
		if (Game.getPlayer2().collide(x, y, width, height))
		{
			sendTo(Game.getPlayer2());
			Game.getEntities().remove(this);
			Game.itemCounter--;
		}
		if (Game.getPlayer3().collide(x, y, width, height))
		{
			sendTo(Game.getPlayer3());
			Game.getEntities().remove(this);
			Game.itemCounter--;
		}
	}

	public abstract void sendTo(Player player_);
}
