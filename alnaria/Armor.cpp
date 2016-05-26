#include "Game.hpp"

Armor::Armor(Char *dad)
{
	if (dad == NULL)
	{
		con("Armor::Armor(Char *dad)");
		return;
	}
	this->dad = dad;
}
