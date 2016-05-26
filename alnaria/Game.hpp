#ifndef GAME_HPP
#define GAME_HPP

#define WIDTH 800
#define HEIGHT 600

#define CHARWIDTH 12 //DOUBLE
#define CHARHEIGHT 20 //DOUBLE

#define all(x) for (unsigned int c = 0; c < x; c++)
#define all2(x, y) for (unsigned int c1 = 0; c1 < x; c1++) for (unsigned int c2 = 0; c2 < y; c2++)
#define con(x) std::cout << x << std::endl
#define key(x) if (sf::Keyboard::isKeyPressed(sf::Keyboard::x))

#include <SFML/Graphics.hpp>
#include <vector>
#include <iostream>
#include "ForwardDeclaration.hpp"

class Game
{
	public:
		static Game& get();
		void run();
		void drawInCam(sf::Sprite*);
		void drawInCam(sf::RectangleShape*);
		sf::RenderWindow* getWindow();
		std::vector<Entity*> entities;
		std::vector<Char*> getChars();
		Level *getLevel();
		sf::Vector2f cam;
		static const int TILESIZE = 20;
	private:
		void handleWindow();
		void init();
		void quit();
		void render();
		void restart();
		void tick();
		sf::RenderWindow *window;
		sf::Event event;
		Level *level;
		Player *player;
};

#include "Include.hpp"

#endif
