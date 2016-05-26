#include "../../Game.hpp"

Template::Template(Char *dad) : Weapon(dad)
{}

void Template::newSkill(int n)
{
	switch (n)
	{
		case 0:
		break;

	}
}

Skill* Template::getStaticSkill(int n)
{
	static Skill* skills[] = {};
	return skills[n];
}
