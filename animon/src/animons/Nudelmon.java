package animons;

import helps.Vi2;
import main.Game;
import animonMain.AnimonController;


public class Nudelmon extends AnimonController{

	public Nudelmon(Vi2 pos) {
		super(pos);
		image = Game.getLoader().load("/animons/nudelmon.png");
		size = new Vi2(8, 8);
	}

	public void tick() {
	}

}
