#include "../../Game.hpp"

Bow::Bow(Char *dad) : Weapon(dad)
{}

void Bow::newSkill(int n)
{
	switch (n)
	{
		case 0:

		break;

	}
}

Skill* Bow::getStaticSkill(int n)
{
	static Skill* skills[] = {};
	return skills[n];
}
