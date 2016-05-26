package cEntity.cBlock.cSpecialBlock;


import cEntity.cBlock.SpecialBlock;
import main.Game;
import other.graphic.ImageLoader;
import other.math.Vec;

public class GreenBlock extends SpecialBlock
{
	public GreenBlock(Vec pos, Vec size)
	{
		super(pos, size);
		image = ImageLoader.greenBlock;
	}

	public void onTouchPlayer()
	{
		Game.get().addScore(-600);
		Game.get().getPlayer().applySave();
		die();
	}
}


