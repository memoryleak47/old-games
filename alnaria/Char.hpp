#ifndef CHAR_HPP
#define CHAR_HPP

#include "Game.hpp"

class Char : public Entity
{
	public:
		Char();							//INIT
		~Char();
		void tick2();
		virtual void tick3() = 0;
		void render();
		Weapon* getWeapon();
		Armor* getArmor();
		sf::Vector2i getLook();					//LOOK-DIRECTION ( -1/0/1 | -1/0/1 )
		float calcSpeed();
		float calcMass();
		int getRenderPriority() {return 1;}
		float getHealth(), getEnergy();

//		bool walkAble;
	protected:
		bool getWalkAble();
		sf::Vector2f offset;
		void setWeapon(Weapon*);
		void setArmor(Armor*);
	private:
		void walk();						//WALKING ANIMATION
		sf::Vector2i look;
		Weapon *weapon;
		Armor *armor;
		sf::RectangleShape *healthBar, *energyBar;
		int animCounter;
		int step;
		bool walkAble;
		float energy, health;
};

#endif
