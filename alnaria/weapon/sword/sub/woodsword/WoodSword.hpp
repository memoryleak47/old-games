#ifndef WOODSWORD_HPP
#define WOODSWORD_HPP

#include "../../../../Game.hpp"

class WoodSword : public Sword
{
	public:
		WoodSword(Char*);
		float getMass() {return 0;}
		float getSharpness() {return 0;}
};

#endif
