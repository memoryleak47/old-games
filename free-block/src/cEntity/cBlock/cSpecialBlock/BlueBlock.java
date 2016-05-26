package cEntity.cBlock.cSpecialBlock;


import cEntity.cBlock.SpecialBlock;
import main.Game;
import other.graphic.ImageLoader;
import other.math.Vec;

public class BlueBlock extends SpecialBlock
{
	public BlueBlock(Vec pos, Vec size)
	{
		super(pos, size);
		image = ImageLoader.blueBlock;
	}

	public void onTouchPlayer()
	{
		Game.get().addScore(600);
		die();
	}
}


