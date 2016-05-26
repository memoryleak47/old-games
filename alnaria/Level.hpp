#ifndef LEVEL_HPP
#define LEVEL_HPP

#include "Game.hpp"

class Level
{
	public:
		Level();
		~Level();
		bool loadLevel(std::string);
		void render();
		Tile* getTile(int, int);
	private:
	public:
		std::vector<sf::Color> colors;
		sf::Sprite *sprite;
		sf::Image *image;
		unsigned int w, h;

};

#endif

