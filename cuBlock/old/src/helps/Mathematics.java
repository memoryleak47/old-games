package helps;

import base.Entity;

public class Mathematics
{

	public static float getMagnitude(float x1_, float y1_, float x2_, float y2_)
	{
		return (float) Math.sqrt((x1_ - x2_) * (x1_ - x2_) + (y1_ - y2_) * (y1_ - y2_));
	}

	public static float getMagnitude(float x_, float y_)
	{
		return (float) Math.sqrt(x_ * x_ + y_ * y_);
	}

	public static float getNormalized(float x_, float y_)
	{
		return x_ / (getMagnitude(x_, y_));
	}

	public static int toOne(int value_)
	{
		int _temp = value_ / Math.abs(value_);
		return _temp;
	}

	public static float toOne(float value_)
	{
		float _temp = value_ / Math.abs(value_);
		return _temp;
	}

	public static float getDistance(Entity e1_, Entity e2_)
	{
		float _distX = e1_.getCenterX() - e2_.getCenterX();
		float _distY = e1_.getCenterY() - e2_.getCenterY();
		return getMagnitude(_distX, _distY);
	}
}
