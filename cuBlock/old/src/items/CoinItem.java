package items;

import main.ImageLoader;
import main.Player;
import base.Item;

public class CoinItem extends Item
{

	public CoinItem(float x_, float y_, float width_, float height_)
	{
		super(x_, y_, width_, height_);
		image = ImageLoader.coinItem;

	}

	public void sendTo(Player player_)
	{
		player_.score++;
	}

}
