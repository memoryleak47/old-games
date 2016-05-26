#ifndef BOW_HPP
#define BOW_HPP

#include "../../Game.hpp"

class Bow : public Weapon
{
	public:
		Bow(Char*);
		void newSkill(int);
		Skill* getStaticSkill(int);
};

#endif
