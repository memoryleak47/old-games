#ifndef SKIN_HPP
#define SKIN_HPP

#include "../../Game.hpp"

class Skin : public Armor
{
	public:
		Skin(Char*);
		float getMass() {return 1;}
		float getDefense() {return 1;}
};

#endif
