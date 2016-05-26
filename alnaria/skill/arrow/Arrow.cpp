#include "../../Game.hpp"

Arrow::Arrow()
{}

Arrow::Arrow(Char *dad) : Skill(dad->getWeapon())
{
	loadTexture("png/tile/sand.png");
	setPos(dad->getSprite()->getPosition());
	unchain();
}


void Arrow::tick3()
{
	getSprite()->move(getVelocity());
	if(getTime() > 1000)
		die();
}

float Arrow::calcDamage(Char *enemy)
{
	return 0;
}

bool Arrow::test(Char *dad, Char *enemy)
{
	return false;
}
