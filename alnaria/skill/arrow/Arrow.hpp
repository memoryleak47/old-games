#ifndef ARROW_HPP
#define ARROW_HPP

#include "../../Game.hpp"

class Arrow : public Skill
{
	public:
		Arrow();
		Arrow(Char*);
		void tick3();
		float calcDamage(Char*);
		bool test(Char*, Char*);
		float getEnergyCost() {return 1;}
};

#endif
