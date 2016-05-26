package helps;

public class V2 {
public float x, y;

	public V2(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public V2(V2 base)
	{
		this.x = base.x;
		this.y = base.y;
	}
	
	
	public static V2 zero(){
		return new V2(0, 0);
	}
	
	public static V2 one(){
		return new V2(1, 1);
	}
	
	public V2 normalized()
	{		
		float scalar = (float)(1/(x+y));
		return new V2(x*scalar, y*scalar);
	}
	
	public float magnitude()
	{
		float temp = x*x + y*y;
		return (float) Math.sqrt(temp);
	}
	
	public V2 multiply(float scalar)
	{
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	public V2 getMultiply(float scalar)
	{
		return new V2(x*scalar, y*scalar);
	}
	
	public V2 add(float adding)
	{
		x += adding;
		y += adding;
		return this;
	}

	public V2 getAdd(float adding)
	{
		return new V2(x+adding, y+adding);
	}
	
	public Vi2 toInt()
	{
		return new Vi2((int)x, (int)y);
	}
	
	public V2 plus (V2 other)
	{
		return new V2(this.x + other.x, this.y + other.y);
	}
	
	public V2 times (float Arg)
	{
		return new V2(this.x * Arg, this.y * Arg);
	}
	
	public float getDistance(V2 other)
	{
		V2 temp = new V2(Math.abs(this.x - other.x), Math.abs(this.y - other.y));
		return temp.magnitude();
	}
	
	public boolean collideCircle(V2 other, float dist)
	{
		if(getDistance(other) < dist)
			return true;
		
		return false;
	}
	
	public boolean collideBox(V2 other, V2 dist)
	{
		if(Math.abs(this.x - other.x) > dist.x)
			return false;
		if(Math.abs(this.y - other.y) > dist.y)
			return false;
		
		return true;
	}
	
	public boolean collideBox(V2 other, float dist)
	{
		if(Math.abs(this.x - other.x) > dist)
			return false;
		if(Math.abs(this.y - other.y) > dist)
			return false;
		
		return true;
	}


}
