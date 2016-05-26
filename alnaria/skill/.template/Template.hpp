#ifndef TEMPLATE_HPP
#define TEMPLATE_HPP

#include "../../Game.hpp"

class Template : public Skill
{
	public:
		Template();
		Template(Char*);
		void tick3();
		float calcDamage(Char*);
		bool test(Char*, Char*);
		float getEnergyCost() {return 0;}
};

#endif
