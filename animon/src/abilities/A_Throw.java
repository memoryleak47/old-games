package abilities;

import helps.V2;
import helps.Vi2;
import main.Game;
import abilityMain.Ability;
import animonMain.CharacterController;

public class A_Throw extends Ability {

	public void init(CharacterController dad, V2 acc, V2 damages) {
	    super.init(dad, acc, damages);
		size = new Vi2(8,8);
		    image = Game.getLoader().load("/abilities/Boomerang.png");
		
	}

	public void tick() {
	     move(acc.toInt());
	}

}
