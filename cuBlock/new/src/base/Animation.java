package base;

import java.awt.image.BufferedImage;

import core.Game;

public class Animation extends Entity
{
	private int duration;

	private boolean switching = false;
	private BufferedImage[]	images;
	private int imageIterator		= 0;
	private int imageChangeCounter	= 0;
	private int imageChangeCounterMaxLength;

	public Animation(Entity dad_, BufferedImage image_, float width_, float height_, int duration_)
	{
		mode = NOCOLLIDE;
		image = image_;
		width = width_;
		height = height_;
		setPosition(dad_);
		duration = duration_;
	}

	public Animation(Entity dad_, BufferedImage[] images_, float width_, float height_, int duration_, int imageChangeCounter_)
	{
		mode = NOCOLLIDE;
		images = images_;
		width = width_;
		height = height_;
		setPosition(dad_);

		duration = duration_;
		switching = true;
		image = images_[0];
		imageChangeCounterMaxLength = imageChangeCounter_;
	}

	public void tick()
	{
		duration--;
		if (duration <= 0)
			Game.getEntities().remove(this);

		if (!switching)
			return;

		if (imageChangeCounter >= imageChangeCounterMaxLength)
		{
			if (imageIterator + 1 < images.length)
			{
				imageIterator++;
			} else
				imageIterator = 0;

			image = images[imageIterator];
			imageChangeCounter = 0;

		} else
		{
			imageChangeCounter++;
		}
	}
}
