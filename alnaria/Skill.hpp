#ifndef SKILL_HPP
#define SKILL_HPP

#include "Game.hpp"

class Skill : public Entity
{
	public:
		Skill();						//just for 'Skill::test(Char*, Char*)'
		Skill(Weapon*);						//sets dad; nulls time;
		~Skill();						//unchains; NULLS all
		void tick2();						//actualize time;
		virtual void tick3() = 0;
		virtual bool test(Char*, Char*) = 0;
		virtual float getEnergyCost() = 0;
		int getRenderPriority() {return 2;}				//
		Char* getDad();
		Weapon* getWeapon();
	protected:
		void die();
		void damageCircle(sf::Vector2f, float);
		float getTime();
		sf::Vector2f getVelocity();
		void unchain();
	private:
		Weapon *weapon;
		float time;
		sf::Vector2f velocity;
		sf::Clock clock;

};

#endif
