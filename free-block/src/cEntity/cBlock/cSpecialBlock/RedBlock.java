package cEntity.cBlock.cSpecialBlock;


import cEntity.cBlock.SpecialBlock;
import main.Game;
import other.graphic.ImageLoader;
import other.math.Vec;

public class RedBlock extends SpecialBlock
{
	public static final float SPEED_INCREASEMENT = 8;

	public RedBlock(Vec pos, Vec size)
	{
		super(pos, size);
		image = ImageLoader.redBlock;
	}

	public void onTouchPlayer()
	{
		Game.get().getPlayer().getVel().change(SPEED_INCREASEMENT, 0);
		Game.get().addScore(1500);
		die();
	}
}


