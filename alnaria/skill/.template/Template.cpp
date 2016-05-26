#include "../../Game.hpp"

Template::Template()
{}

Template::Template(Char *dad) : Skill(dad)
{
}

void Template::tick3()
{
}

float Template::calcDamage(Char *enemy)
{
	return 0;
}

bool Template::test(Char *dad, Char *enemy)
{
	return false;
}
