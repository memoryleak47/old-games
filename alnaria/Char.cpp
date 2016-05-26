#include "Game.hpp"

Char::Char()
{
	healthBar = new sf::RectangleShape();
	energyBar = new sf::RectangleShape();

	energy = 2;
	health = 42;
	healthBar->setFillColor(sf::Color::Red);
	energyBar->setFillColor(sf::Color::Yellow);
	setWeapon(NULL);
	setArmor(NULL);
	walkAble = true;
	eState = eCHAR;
}

Char::~Char()
{
	delete healthBar;
	delete energyBar;
}

void Char::tick2()
{
	tick3();
	walk();
	if (offset != sf::Vector2f(0, 0))
		look = help::toOneV(offset);
	offset = sf::Vector2f(0, 0);
}

void Char::render()
{
	Game::get().drawInCam(getSprite());

	healthBar->setSize(sf::Vector2f(health, 5));
	healthBar->setOrigin(sf::Vector2f(healthBar->getSize().x/2, 0));
	healthBar->setPosition(getSprite()->getPosition() + sf::Vector2f(getSprite()->getGlobalBounds().width/2, -10));

	energyBar->setSize(sf::Vector2f(energy, 5));
	energyBar->setPosition(getSprite()->getPosition() + sf::Vector2f(getSprite()->getGlobalBounds().width/2, -5));
	energyBar->setOrigin(sf::Vector2f(energyBar->getSize().x/2, 0));

	Game::get().drawInCam(healthBar);
	Game::get().drawInCam(energyBar);
}

void Char::setWeapon(Weapon *weapon)
{
	if (weapon == NULL)
		weapon = new Fist(this);
	this->weapon = weapon;
}

Weapon* Char::getWeapon()
{
	return weapon;
}

void Char::setArmor(Armor *armor)
{
	if (armor == NULL)
		armor = new Skin(this);
	this->armor = armor;
}

Armor* Char::getArmor()
{
	return armor;
}

sf::Vector2i Char::getLook()
{
	return look;
}

float Char::getHealth()
{
	return health;
}

float Char::getEnergy()
{
	return energy;
}

bool Char::getWalkAble()
{
	return walkAble;
}

void Char::walk()
{
	animCounter++;
	if (look != help::toOneV(offset))
		animCounter = step = 0;

	if (animCounter > 10)
	{
		step++;
		animCounter = 0;
		if (step > 2)
			step = 0;
	}
		getSprite()->setTextureRect(sf::IntRect(CHARWIDTH+look.x*CHARWIDTH+CHARWIDTH*3*step, CHARHEIGHT+look.y*CHARHEIGHT, CHARWIDTH, CHARHEIGHT));

	move(offset*calcSpeed());
}

float Char::calcSpeed()
{
	return energy; // return energy/calcMass();
}

float Char::calcMass()
{
	float temp = 10;
	temp += getWeapon()->getMass();
	temp += getArmor()->getMass();
	return temp;
}
