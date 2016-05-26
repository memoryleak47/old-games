package items;

import core.ImageLoader;
import core.Player;
import base.Item;

public class GravityItem extends Item
{

	public GravityItem(float x_, float y_)
	{
		super(x_, y_);
		image = ImageLoader.gravityItem;
	}

	public void sendTo(Player player_)
	{
		GRAVITY *= -1;	
	}

}
