#include "../../Game.hpp"

Sword::Sword(Char *dad) : Weapon(dad)
{}

void Sword::newSkill(int n)
{

	switch (n)
	{
		case 0:

		break;

	}
}

Skill* Sword::getStaticSkill(int n)
{
	static Skill* skills[] = {};
	return skills[n];
}
