package animonMain;

import helps.V2;
import helps.Vi2;

import java.awt.Graphics;

import main.Entity;
import main.Game;
import main.ImageLoader;
import abilityMain.Ability;
import abilityMain.AbilitySaver;

public abstract class CharacterController extends Entity {
    
    protected V2 health, energy, exp, physicalDamage, specialDamage,
	    physicalDefense, specialDefense;

    protected int[] abilityIDs;

    protected CharacterController(Vi2 posArg) {
	super(posArg);

	abilityIDs = new int[4];
	abilityIDs[0] = 1;
	abilityIDs[1] = 2;
	abilityIDs[2] = 0;
	abilityIDs[3] = 0;

	health = V2.one().times(100);
	energy = V2.one().times(100);
	exp = V2.one().times(100);
	exp.y = 0;
	physicalDamage = V2.one().times(100);
	specialDamage = V2.one().times(100);
	physicalDefense = V2.one().times(100);
	specialDefense = V2.one().times(100);
    }

    // ABILITY STUFF

    public void OnPhysicalAttacked(float strength) {
	health.y -= strength / physicalDefense.y;
    }

    public void OnSpecialAttacked(float strength) {
	health.y -= strength / specialDefense.y;
    }

    public void OnTrueAttacked(float strength) {
	health.y -= strength;
    }

    public void useAbility(int abilityID) {
	
	Ability a = AbilitySaver.getAbility(abilityID);
	Game.getActiveAbilities().add(a);
	a.init(this, acc, new V2(physicalDamage.y, specialDamage.y));
    }
    public void render(Graphics g)
    {
	super.render(g);
	displayBars(g);
	
	recalc();
    }
    public void displayBars(Graphics g)
    {
	g.drawImage(ImageLoader.healthBar,
		pos.x - Game.getPlayer().getCameraOffset().x,
		pos.y - Game.getPlayer().getCameraOffset().y - 20,
		(int)((health.y/health.x) * Game.SCALE * 10),
		5,
		null);
	
	g.drawImage(ImageLoader.energyBar,
		pos.x - Game.getPlayer().getCameraOffset().x,
		pos.y - Game.getPlayer().getCameraOffset().y - 15,
		(int)((energy.y/energy.x) * Game.SCALE * 10),
		5,
		null);
	
	g.drawImage(ImageLoader.expBar,
		pos.x - Game.getPlayer().getCameraOffset().x,
		pos.y - Game.getPlayer().getCameraOffset().y - 10,
		(int)((exp.y/exp.x) * Game.SCALE * 10),
		5,
		null);
	
    }
    
    void recalc()
    {
	if(health.y <= 0)
	{
	   die();
	}
    }
    
    void die()
    {
	if(isPlayer)
	    System.out.println("dead player");
	else
	 for (int i = 0; i < Game.getAnimons().size(); i++)
	 {
	     if(Game.getAnimons().get(i) == this)
		 Game.getAnimons().remove(i);
	 }
    }
    
}
