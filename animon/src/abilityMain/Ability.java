package abilityMain;

import helps.V2;
import helps.Vi2;
import main.Entity;
import main.Game;
import animonMain.CharacterController;

public abstract class Ability extends Entity {

    protected float range;
    protected CharacterController dad;
    public float neededEnergy;
    public boolean active = false;

    public final int PHYSICAL = 0;
    public final int SPECIAL = 1;
    public final int TRUE = 2;

    protected V2 damages;

    public void init(CharacterController dad, V2 acc, V2 damages) {
	this.dad = dad;
	pos = new Vi2(dad.getOrigin()); // getting them independent, noodle
	this.acc = acc;
	this.damages = new V2(damages); // getting them independent, noodle
	active = true;
    }

    protected void die() {
	for (int i = 0; i < Game.getActiveAbilities().size(); i++) {
	    if (Game.getActiveAbilities().get(i) == this)
		Game.getActiveAbilities().remove(i);
	}
    }

    public abstract void tick();

}
