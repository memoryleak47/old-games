package animons;
import helps.V2;
import helps.Vi2;

import main.Game;
import main.KeyManager;
import abilityMain.AbilitySaver;
import animonMain.PlayerController;

public class Player extends PlayerController {
	private final int SPEED = 3;
	


	public Player(Vi2 pos) {
		super(pos);
		image = Game.getLoader().load("/animons/player.png");
		size = new Vi2(8, 8);
	}

	public void tick() {
acc = V2.zero();

		acc.y =  (KeyManager.up) ? acc.y-SPEED : acc.y;
		acc.y = (KeyManager.dn) ? acc.y+SPEED : acc.y;
		acc.x = (KeyManager.lt) ? acc.x-SPEED : acc.x;
		acc.x = (KeyManager.rt) ? acc.x+SPEED : acc.x;

		move(acc.toInt());
		inputAbilities();
	}
	
	void inputAbilities()
	{
		if(KeyManager.q && AbilitySaver.getAbility(abilityIDs[0]).neededEnergy < energy.y){
			useAbility(abilityIDs[0]);
		KeyManager.q = false;
		}
		if(KeyManager.w && AbilitySaver.getAbility(abilityIDs[1]).neededEnergy < energy.y){
			useAbility(abilityIDs[1]);
		KeyManager.w = false;
		}
		if(KeyManager.e && AbilitySaver.getAbility(abilityIDs[2]).neededEnergy < energy.y){
			useAbility(abilityIDs[2]);
		KeyManager.e = false;
		}
		if(KeyManager.r && AbilitySaver.getAbility(abilityIDs[3]).neededEnergy < energy.y){
			useAbility(abilityIDs[3]);
		KeyManager.r = false;
		}
	}


}
