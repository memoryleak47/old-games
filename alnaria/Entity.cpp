#include "Game.hpp"

Entity::Entity()
{
	eState = eDEFAULT;
	texture = new sf::Texture();
	sprite = new sf::Sprite(*texture);
	sprite->setTextureRect(sf::IntRect(0, 0, 100, 100));
	sprite->setScale(1, 1);
}

Entity::~Entity()
{
	delete sprite;
	delete texture;
	removeFromEntities();
}

bool Entity::collideTerrain()
{
	sf::FloatRect temp;
	temp.width = temp.height = Game::TILESIZE;
	int begin1 = sprite->getGlobalBounds().left/Game::TILESIZE;
	int begin2 = sprite->getGlobalBounds().top/Game::TILESIZE;
	int end1 = (sprite->getGlobalBounds().left+sprite->getGlobalBounds().width)/Game::TILESIZE+1;
	int end2 = (sprite->getGlobalBounds().top+sprite->getGlobalBounds().height)/Game::TILESIZE+1;
	for (int c1 = begin1; c1 < end1; c1++)
	for (int c2 = begin2; c2 < end2; c2++)
	{
		temp.left = c1*Game::TILESIZE;
		temp.top = c2*Game::TILESIZE;
		if (Game::get().getLevel()->getTile(c1, c2)->isSolid() && temp.intersects(sprite->getGlobalBounds()))
			return true;
	}
	return false;
}

void Entity::move(const sf::Vector2f &v)
{
	sprite->move(v.x, 0);
	if (collideTerrain())
		sprite->move(-v.x, 0);
	sprite->move(0, v.y);
	if (collideTerrain())
		sprite->move(0, -v.y);
}

void Entity::tick1()
{
	tick2();
}

void Entity::render()
{
	Game::get().drawInCam(sprite);
}

bool Entity::collide(Entity *other)
{
	if (other == NULL)
	{
		con("Entity::collide(Entity *other==NULL)");
		return false;
	}
	return sprite->getGlobalBounds().intersects(other->sprite->getGlobalBounds());
}

void Entity::moveIn(Entity *other)
{
	if (other == NULL)
	{
		con("Entity::moveIn(Entity *other==NULL)");
		return;
	}
	sprite->setPosition(other->getSprite()->getPosition() + other->getSprite()->getOrigin()/2.f - sprite->getOrigin()/2.f); //DOESNT WORK
}

void Entity::setPos(const sf::Vector2f &v)
{
	sprite->setPosition(v);
}

int Entity::getRenderPriority()
{
	return 0;
}

bool Entity::loadTexture(const std::string& path)
{
	if (!texture->loadFromFile(path))
		return false;
	delete sprite;
	sprite = new sf::Sprite(*texture);
	return true;
}

sf::Sprite* Entity::getSprite()
{
	return sprite;
}

void Entity::removeFromEntities()
{
	all(Game::get().entities.size())
		if (Game::get().entities[c] == this)
			Game::get().entities.erase(Game::get().entities.begin() + c);
}
