package items;

import core.ImageLoader;
import core.Player;
import base.Item;

public class SpeedItem extends Item
{

	public SpeedItem(float x_, float y_)
	{
		super(x_, y_);
		image = ImageLoader.speedItem;
	}

	public void sendTo(Player player_)
	{
		player_.speed = player_.STD_SPEED * 2;
	}

}
