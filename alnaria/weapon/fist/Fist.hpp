#ifndef FIST_HPP
#define FIST_HPP

#include "../../Game.hpp"

class Fist : public Weapon
{
	public:
		Fist(Char*);
		void newSkill(int);
		Skill* getStaticSkill(int);
		float getMass() {return 0;}
};

#endif
