#include "Game.hpp"

Skill::Skill()
{}

Skill::Skill(Weapon *weapon) : time(0), velocity(sf::Vector2f(weapon->getDad()->getLook().x, weapon->getDad()->getLook().y) * weapon->getDad()->calcSpeed())
{
	if (weapon == NULL)
	{
		con("Skill::Skill(Weapon *weapon==NULL)");
		return;
	}
	eState = eSKILL;
	this->weapon = weapon;
	weapon->able = false;
	Game::get().entities.push_back(this);
}

Skill::~Skill()
{
	if (weapon)
		unchain();
}

void Skill::unchain()
{
	getWeapon()->unchain_setSkill(NULL);
	getWeapon()->able = true;
	weapon = NULL;
}

void Skill::tick2()
{
	time += clock.restart().asMilliseconds();
	tick3();
}

Char* Skill::getDad()
{
	return getWeapon()->getDad();
}

Weapon* Skill::getWeapon()
{
	return weapon;
}

void Skill::die()
{
	delete this;
}

void Skill::damageCircle(sf::Vector2f, float radius)
{

}

float Skill::getTime()
{
	return time;
}

sf::Vector2f Skill::getVelocity()
{
	return velocity;
}
