#include "Game.hpp"

Level::Level()
{
	sprite = new sf::Sprite();
	image = new sf::Image();
}

Level::~Level()
{
	delete sprite;
	delete image;
}

bool Level::loadLevel(std::string s)
{
	if (!image->loadFromFile(s))
		return false;

	//FAIL			x & y confuse
	w = image->getSize().x;
	h = image->getSize().y;

//	con((int) image->getPixel(1, 0).b);					//CORRECT

	for (unsigned int c2 = 0; c2 < h; c2++)
	for (unsigned int c1 = 0; c1 < w; c1++)
	{
		colors.push_back(image->getPixel(c1,c2));			//MAY HERE
	}
	return true;
}

void Level::render()
{
//	con((getTile(1, 0) == Tile::colorToTile(sf::Color(0, 0, 255))) );	//WRONG

	for (unsigned int c1 = 0; c1 < w; c1++)
	for (unsigned int c2 = 0; c2 < h; c2++)
	{
		sprite->setPosition(sf::Vector2f(c1*Game::TILESIZE,c2*Game::TILESIZE));
		sprite->setTexture(*getTile(c1,c2)->getTexture());
		Game::get().drawInCam(sprite);
	}
}

Tile* Level::getTile(int x, int y)
{
	return Tile::colorToTile(colors[x +y*w]); // WHY? NORMAL: [x +y*w]; [x*w +y] worx
}
