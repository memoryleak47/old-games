package helps;

public class Vi2 {
public int x, y;

	public Vi2(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Vi2(Vi2 base)
	{
		this.x = base.x;
		this.y = base.y;
	}
	
	public static Vi2 zero(){
		return new Vi2(0, 0);
	}
	
	public static Vi2 one(){
		return new Vi2(1, 1);
	}
	
	public Vi2 normalized()
	{		
		float scalar = (int)(1/(x+y));
		return new Vi2((int)(x*scalar), (int)(y*scalar));
	}
	
	public float magnitude()
	{
		float temp = x*x + y*y;
		return (float) Math.sqrt(temp);
	}
	
	public Vi2 multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	public Vi2 getMultiply(float scalar)
	{
		return new Vi2((int)(x*scalar), (int)(y*scalar));
	}
	
	public Vi2 add(float adding)
	{
		x += adding;
		y += adding;
		return this;
	}

	public Vi2 getAdd(int adding)
	{
		return new Vi2(x+adding, y+adding);
	}
	
	public Vi2 plus (Vi2 other)
	{
		return new Vi2(this.x + other.x, this.y + other.y);
	}
	
	public Vi2 times (int Arg)
	{
		return new Vi2(this.x * Arg, this.y * Arg);
	}
	
	public float getDistance(Vi2 other)
	{
		V2 temp = new V2(Math.abs(this.x - other.x), Math.abs(this.y - other.y));
		return temp.magnitude();
	}
	
	public boolean collideCircle(Vi2 other, float dist)
	{
		if(getDistance(other) < dist)
			return true;
		
		return false;
	}
	
	public boolean collideBox(Vi2 other, Vi2 dist)
	{
		if(Math.abs(this.x - other.x) > dist.x)
			return false;
		if(Math.abs(this.y - other.y) > dist.y)
			return false;
		
		return true;
	}
	
	public boolean collideBox(Vi2 other, float dist)
	{
		if(Math.abs(this.x - other.x) > dist)
			return false;
		if(Math.abs(this.y - other.y) > dist)
			return false;
		
		return true;
	}

}
