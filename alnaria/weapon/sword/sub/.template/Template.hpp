#ifndef TEMPLATE_HPP
#define TEMPLATE_HPP

#include "../../../../Game.hpp"

class Template : public Sword
{
	public:
		Template(Char*);
		float getMass()	{return 0;}
		float getSharpness() {return 0;}
};

#endif
