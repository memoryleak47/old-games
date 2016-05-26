package other.math;



public class Vec //tor
{
	private float x, y;

	public Vec(Vec arg)
	{
		x = arg.x;
		y = arg.y;
	}

	public Vec(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	public float getX()
	{
		return x;
	}

	public float getY()
	{
		return y;
	}

	public void change(Vec arg)
        {
                x += arg.x;
		y += arg.y;
        }

        public void change(float x, float y)
        {
                this.x += x;
                this.y += y;
        }

}
