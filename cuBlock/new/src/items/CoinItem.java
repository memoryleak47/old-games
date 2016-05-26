package items;

import core.ImageLoader;
import core.Player;
import base.Item;

public class CoinItem extends Item
{

	public CoinItem(float x_, float y_)
	{
		super(x_, y_);
		image = ImageLoader.coinItem;

	}

	public void sendTo(Player player_)
	{
		player_.score++;
	}

}
