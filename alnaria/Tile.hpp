#ifndef TILE_HPP
#define TILE_HPP

#include "Game.hpp"

class Tile
{
	public:
		Tile(std::string, bool);
		~Tile();
		static Tile* colorToTile(sf::Color);
		sf::Texture* getTexture();
		bool isSolid(); // height ?
	private:
		sf::Texture *texture;
		bool solid;
};

#endif
