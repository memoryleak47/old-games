#include "Game.hpp"

Player::Player()
{
	loadTexture("png/char/player/player.png");
	setPos(sf::Vector2f(70, 50));
}

void Player::tick3()
{
	int n = -1;
	if (getWalkAble())
	{
		key(Up)
			offset.y--;
		key(Down)
			offset.y++;
		key(Right)
			offset.x++;
		key(Left)
			offset.x--;
	}

	key(Q)
		n = 0;
	key(W)
		n = 1;
	key(E)
		n = 2;
	key(R)
		n = 3;
	if (n != -1)
	{
		key(LShift)
			n += 4;
		getWeapon()->trySkill(n);
	}
		Game::get().cam = getSprite()->getPosition() - sf::Vector2f(WIDTH/2, HEIGHT/2);

}
