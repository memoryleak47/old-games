#ifndef ARMOR_HPP
#define ARMOR_HPP

#include "Game.hpp"

class Armor
{
	public:
		Armor(Char*);
		virtual float getMass() = 0;
		virtual float getDefense() {return 1;}
	private:
		Char *dad;
};

#endif
