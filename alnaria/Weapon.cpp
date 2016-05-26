#include "Game.hpp"

Weapon::Weapon(Char *dad)
{
	if (getDad() == NULL)
	{
		con("Weapon::Weapon(Char *dad==NULL)");
		return;
	}
	this->dad = dad;
}

void Weapon::trySkill(int n)
{
	if (n < 0 || n > 7)
		return;
	if (getStaticSkill(n)->getEnergyCost() > getDad()->getEnergy())
		return;
	if (!able)
		return;

//	getDad()->getEnergy() -= getSkill(n)->getEnergyCost();

	newSkill(n);
}

Char* Weapon::getDad()
{
	return dad;
}

Skill* Weapon::getSkill()
{
	return skill;
}

void Weapon::setSkill(Skill* s)
{
	skill = s;
}
