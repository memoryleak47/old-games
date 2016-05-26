#ifndef WEAPON_HPP
#define WEAPON_HPP

#include "Game.hpp"

class Weapon
{
	public:
		Weapon(Char*);
		void trySkill(int);				//Called from Char
		virtual float getMass() = 0;
		virtual float getSharpness() {return 0;}
		Char* getDad();
		Skill* getSkill();
		void unchain_setSkill(Skill *s) {skill = s;}
		bool able;
	protected:
		void setSkill(Skill*);					//needs change while using newSkill
	private:
		virtual void newSkill(int) = 0;
		virtual Skill* getStaticSkill(int) = 0;
		Char *dad;
		Skill *skill;


//	friend void Skill::unchain();
};

#endif
