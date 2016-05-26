#ifndef PLAYER_HPP
#define PLAYER_HPP

#include "Game.hpp"

class Player : public Char
{
	public:
		Player();						//sets Texture/Sprite
		void tick3();						//move; ability; cam-setting;
};

#endif
