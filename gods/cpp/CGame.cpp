#include "CGame.hpp"

CGame::CGame()
{
    init();
}
void CGame::init()
{
}

void CGame::uninit()
{
}

void CGame::run()
{
    float clockCounter = 0;
    sf::Clock clock;
    window = new sf::RenderWindow(sf::VideoMode(ScW, ScH), "TheGods", sf::Style::Default);
    player = new CPlayer;

    while (window->isOpen())
    {
        sf::Event event;
        window->pollEvent(event);
        if (event.type == sf::Event::Closed)
            window->close();

        clockCounter += clock.restart().asMicroseconds();
        if(clockCounter >= 2000)
        {
            onTick();
            clockCounter = 0;
        }
    }
    uninit();
    delete window;
}

CGame& CGame::getInstance()
{
    static CGame instance;
    return instance;
}

void CGame::onTick()
{
    player->update();
    for (unsigned int i = 0; i < player->arrows.size(); i++)
        player->arrows[i]->update();


    //draw
    window->draw(player->sprite);
    for (unsigned int i = 0; i < player->arrows.size(); i++)
    window->draw(player->arrows[i]->sprite);
    window->display();
    window->clear(sf::Color::White);

} // onTick()

