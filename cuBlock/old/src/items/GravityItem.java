package items;

import main.ImageLoader;
import main.Player;
import base.Item;

public class GravityItem extends Item
{

	public GravityItem(float x_, float y_, float width_, float height_)
	{
		super(x_, y_, width_, height_);
		image = ImageLoader.gravityItem;
	}

	public void sendTo(Player player_)
	{
		GRAVITY *= -1;	
	}

}
