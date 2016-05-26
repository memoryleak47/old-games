#ifndef TEMPLATE_HPP
#define TEMPLATE_HPP

#include "../../Game.hpp"

class Template : public Armor
{
	public:
		Template(Char*);
		float getMass() {return 0;}
		float getDefense() {return 0;}
};

#endif
