#include "../../Game.hpp"

Fist::Fist(Char *dad) : Weapon(dad)
{}

void Fist::newSkill(int n)
{
	switch (n)
	{
		case 0:
			setSkill(new Punch(getDad()));
		break;
		case 1:
			setSkill(new Arrow(getDad()));
		break;
	}
}

Skill* Fist::getStaticSkill(int n)
{
	static Skill* skills[] = {new Punch(), new Arrow()};
	return skills[n];
}
