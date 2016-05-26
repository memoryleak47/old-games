package items;

import main.ImageLoader;
import main.Player;
import base.Item;

public class SpeedItem extends Item
{

	public SpeedItem(float x_, float y_, float width_, float height_)
	{
		super(x_, y_, width_, height_);
		image = ImageLoader.speedItem;
	}

	public void sendTo(Player player_)
	{
		player_.speed = player_.STD_SPEED * 2;
	}

}
