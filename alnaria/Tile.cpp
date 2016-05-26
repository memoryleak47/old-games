#include "Game.hpp"

Tile::Tile(std::string path, bool solid)
{
	texture = new sf::Texture();
	texture->loadFromFile(path);
	this->solid = solid;
}

Tile::~Tile()
{
	delete texture;
}

Tile* Tile::colorToTile(sf::Color color)
{
	static Tile *grass = new Tile("png/tile/grass.png", false);
	static Tile *tree = new Tile("png/tile/tree.png", true);
	static Tile *sand = new Tile("png/tile/sandway.png", false);
	static Tile *water = new Tile("png/tile/water.png", false);
	static Tile *rock = new Tile("png/tile/rock.png", true);

	if (color == sf::Color(0, 255, 0))
		return grass;
	else if (color == sf::Color(20, 75, 20))
		return tree;
	else if (color == sf::Color(200, 200, 0))
		return sand;
	else if (color == sf::Color(0, 0, 255))
		return water;
	else if (color == sf::Color(75, 75, 75))
		return rock;

	return sand;
}

sf::Texture* Tile::getTexture()
{
	return texture;
}

bool Tile::isSolid()
{
	return solid;
}
