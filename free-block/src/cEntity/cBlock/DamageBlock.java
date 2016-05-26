package cEntity.cBlock;

import cEntity.Block;
import main.Game;
import other.math.Vec;
import other.graphic.ImageLoader;

public class DamageBlock extends Block
{
	static final private int DAMAGE_AMOUNT = 20;

	public DamageBlock(Vec pos, Vec size)
	{
		super(pos, size);
		image = ImageLoader.damageBlock;
	}	

	public void onTouchPlayer()
	{
		Game.get().restart();
	}
}
