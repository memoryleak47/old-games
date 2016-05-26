#include "Game.hpp"

Game& Game::get()
{
	static Game game;
	return game;
}

void Game::run()
{
	init();

	sf::Clock clock;
	float clockCounter = 0;

	window = new sf::RenderWindow(sf::VideoMode(WIDTH, HEIGHT), "Alnaria");
	while (window->isOpen())
	{
		clockCounter += clock.restart().asMilliseconds();
		handleWindow();
		render();
		if (clockCounter > 1)
		{
			tick();
			clockCounter = 0;
		}
	}
}

void Game::drawInCam(sf::Sprite *sprite)
{
	if (sprite == NULL)
	{
		con("Game::drawInCam(sf::Sprite *sprite==NULL)");
		return;
	}
	sprite->move(-cam);
	window->draw(*sprite);
	sprite->move(cam);
}

void Game::drawInCam(sf::RectangleShape *shape)
{
	if (shape == NULL)
	{
		con("Game::drawInCam(sf::RectangleShape *shape=NULL");
		return;
	}
	shape->move(-cam);
	window->draw(*shape);
	shape->move(cam);
}

std::vector<Char*> Game::getChars()
{
	con("Game::getChars()");
	std::vector<Char*> temp;
	all(entities.size())
		if (entities[c]->eState == eCHAR)
			temp.push_back((Char*)entities[c]);
	return temp;
}

Level *Game::getLevel()
{
	return level;
}

void Game::handleWindow()
{
	window->pollEvent(event);
	if (event.type == sf::Event::Closed)
		quit();
}

void Game::init()
{
	restart();
}

void Game::quit()
{
	window->close();
	delete window;
	entities.clear();
	delete level;
}

void Game::render()
{
	window->clear();
	level->render();		//ERROR HERE
	for (int rp = 0; rp < 10; rp++)
		all(entities.size())
			if (rp == entities[c]->getRenderPriority())
				entities[c]->render();
	window->display();
}

void Game::tick()
{
	all(entities.size())
		entities[c]->tick1();
}

void Game::restart()
{
	level = new Level();
	player = new Player();
	level->loadLevel("png/level/level1.png");
	entities.push_back(player);
}
