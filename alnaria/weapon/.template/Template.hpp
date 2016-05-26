#ifndef TEMPLATE_HPP
#define TEMPLATE_HPP

#include "../../Game.hpp"

class Template : public Weapon
{
	public:
		Template(Char*);
		void newSkill(int);
		Skill* getStaticSkill(int);
};

#endif
