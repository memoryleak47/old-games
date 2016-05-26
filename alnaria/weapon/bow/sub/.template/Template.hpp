#ifndef TEMPLATE_HPP
#define TEMPLATE_HPP

#include "../../../../Game.hpp"

class Template : public Bow
{
	public:
		Template(Char*);
		float getMass() {return 0;}
};

#endif
