package abilities;

import helps.V2;
import helps.Vi2;
import main.Game;
import abilityMain.Ability;
import animonMain.CharacterController;

public class A_Hit extends Ability {
 

	public void init(CharacterController dad, V2 acc, V2 damages) {
	    super.init(dad, acc, damages);
		size = new Vi2(80,80);
		pos = new Vi2(dad.getOrigin().plus(dad.getSize().plus(this.getSize()).times(-1)));
		    image = Game.getLoader().load("/abilities/Boomerang.png");
		    
		   // explode();
	}

	public void tick(){}
	
	void explode()
	{
	    for (int i = 0; i < Game.getLivingObjects().size(); i++)
	    {
		if(Game.getLivingObjects().get(i) != dad && this.collideBox(Game.getLivingObjects().get(i)))
	   {
		    Game.getLivingObjects().get(i).OnPhysicalAttacked(10*damages.x);
	   System.out.println("LOL");
	   }  }
	    die();
	}

}
