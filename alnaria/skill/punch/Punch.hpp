#ifndef PUNCH_HPP
#define PUNCH_HPP

#include "../../Game.hpp"

class Punch : public Skill
{
	public:
		Punch();
		Punch(Char*);
		void tick3();
		bool test(Char*, Char*);
		float getEnergyCost() {return 1;}
};

#endif
