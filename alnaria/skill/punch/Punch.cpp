#include "../../Game.hpp"

Punch::Punch()
{}

Punch::Punch(Char *dad) : Skill(dad->getWeapon())
{
	loadTexture("png/tile/sand.png");
}

void Punch::tick3()
{
	moveIn(getDad());
	if (getTime() > 1000)
		die();
}

bool Punch::test(Char *dad, Char *enemy)
{
	return false;
}
