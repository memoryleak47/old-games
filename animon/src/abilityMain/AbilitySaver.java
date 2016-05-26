package abilityMain;

import abilities.A_Hit;
import abilities.A_Throw;
import abilities.A_Void;

public class AbilitySaver {
	
	public static Ability getAbility(int AbilityID)
	{
	    switch(AbilityID)
	    {
	    case 0:
	    {
		return new A_Void();
	    }
	    case 1:
	    {
		return new A_Hit();
	    }
	    case 2:
	    {
		return new A_Throw();
	    }
default:
	    {
		return null;
	    }
	    }
	}
	
}
