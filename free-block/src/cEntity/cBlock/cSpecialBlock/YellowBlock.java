package cEntity.cBlock.cSpecialBlock;


import cEntity.cBlock.SpecialBlock;
import main.Game;
import other.graphic.ImageLoader;
import other.math.Vec;

public class YellowBlock extends SpecialBlock
{
	public YellowBlock(Vec pos, Vec size)
	{
		super(pos, size);
		image = ImageLoader.yellowBlock;
	}

	public void onTouchPlayer()
	{
		Game.get().addScore(600);
		Game.get().getPlayer().applyBanana();
		die();
	}
}


