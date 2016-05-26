package main;

import helps.V2;
import helps.Vi2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public boolean isPlayer = false;
	public static Vi2 cameraOffset = new Vi2(0, 0);

    protected V2 acc;

    protected BufferedImage image;
    protected Vi2 pos, size;

    protected Entity() {}
    public Entity(Vi2 posArg) {
	pos = posArg;
	acc = new V2(V2.zero());
    }

    public void render(Graphics g) {
	g.drawImage(image, pos.x - Game.getPlayer().getCameraOffset().x, pos.y
		- Game.getPlayer().getCameraOffset().y, size.x * Game.SCALE,
		size.y * Game.SCALE, null);
    }

    public Vi2 getCenter() {
	return new Vi2((int) (pos.x + (size.x * 0.5f)),
		(int) (pos.y + (size.y * 0.5f)));
    }

    public Vi2 getOrigin() {
	return pos;
    }

    public Vi2 getSize() {
	return size;
    }
    
    public void move(Vi2 acc) {
	if (!collision(new Vi2(acc.x, 0))) {
		pos.x += acc.x;
	}
	if (!collision(new Vi2(0, acc.y))){
		pos.y += acc.y;
	}

}

private boolean collision(Vi2 acc) {
	
	if (Game.getLevel()
			.getTile((acc.x + pos.x) / (Game.TILESIZE * Game.SCALE),
					(acc.y + pos.y) / (Game.TILESIZE * Game.SCALE)).isSolid())
		return true;
	
	if (Game.getLevel()
			.getTile(
					(acc.x + pos.x + size.x * Game.SCALE - 1)
							/ (Game.TILESIZE * Game.SCALE),
					(acc.y + pos.y) / (Game.TILESIZE * Game.SCALE)).isSolid())
		return true;
	
	if (Game.getLevel()
			.getTile((acc.x + pos.x) / (Game.TILESIZE * Game.SCALE),
					(acc.y + pos.y + size.y * Game.SCALE -1) / (Game.TILESIZE * Game.SCALE)).isSolid())
		return true;
	
	if (Game.getLevel()
			.getTile((acc.x + pos.x + size.x * Game.SCALE -1) / (Game.TILESIZE * Game.SCALE),
					(acc.y + pos.y + size.y * Game.SCALE -1) / (Game.TILESIZE * Game.SCALE)).isSolid())
		return true;

	return false;
}

    
    public abstract void tick();


	
	
	public Vi2 getCameraOffset()
	{
	    return cameraOffset;
	}
	
	    
	    public boolean collideBox(Entity other)
	    {
		V2 dist = new V2((this.getSize().x + other.getSize().x) * 0.5f, (this.getSize().y + other.getSize().y) * 0.5f);
		if(Math.abs(this.getCenter().x - other.getCenter().x) > dist.x)
			return false;
		if(Math.abs(this.getCenter().y - other.getCenter().y) > dist.y)
			return false;
		
		System.out.println("LOLYA");
		return true;
	    }

	    public static boolean collideCirce(Entity other)
	    {
		return false;
	    }
}
