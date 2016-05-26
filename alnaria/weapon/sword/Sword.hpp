#ifndef SWORD_HPP
#define SWORD_HPP

#include "../../Game.hpp"

class Sword : public Weapon
{
	public:
		Sword(Char*);
		void newSkill(int);
		Skill* getStaticSkill(int);
};

#endif
