#ifndef WOODBOW_HPP
#define WOODBOW_HPP

#include "../../../../Game.hpp"

class WoodBow : public Bow
{
	public:
		WoodBow(Char*);
		float getMass() {return 0;}
};

#endif
