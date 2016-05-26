#ifndef ENTITY_HPP
#define ENTITY_HPP

#include "Game.hpp"

enum EState
{eDEFAULT, eDEAD, eCHAR, eSKILL};

class Entity
{
	public:
		Entity();						//eState = eDEFAULT;
		virtual ~Entity();					//removeFromEntity();
		bool collideTerrain();					//checks TerrainCollision
		void move(const sf::Vector2f&);					//moves (if no collision)
		void tick1();						//tick2();
		virtual void tick2() = 0;
		virtual void render();
		bool collide(Entity*);					//checks EntityCollision
		void moveIn(Entity*);					//doesnt work
		EState eState;
		void setPos(const sf::Vector2f&);
		virtual int getRenderPriority();			//std = 0; 10 MAX; 0 MIN
	protected:
		bool loadTexture(const std::string&);
	public:	
		sf::Sprite* getSprite();
	private:
		void removeFromEntities();				//erases 'this' out of entities, should just be in Entity::~Entity
		sf::Sprite *sprite;
		sf::Texture *texture;
};

#endif
