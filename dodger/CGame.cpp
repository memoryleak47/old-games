#include "CGame.hpp"
#include <iostream>

CGame::CGame()
{
counter = 0;
active = true;
}

void CGame::run()
{

player = new CPlayer;
window = new sf::RenderWindow(sf::VideoMode(ScW, ScH), "dodger..");

    while (window->isOpen())
    {
        sf::Event event;
        window->pollEvent(event);
        if (event.type == sf::Event::Closed)
            window->close();

            onTick(clock.restart().asMilliseconds());
    }
}

CGame& CGame::getInstance()
{
    static CGame instance;
    return instance;
}
void CGame::onTick(int millis)
{
draw();
walk(millis);
throwBombs(millis);

std::cout << "HIGHSCORE: " << highscore << ". SCORE: " << score << std::endl;
score += millis;
}

void CGame::walk (int millis)
{
if (sf::Keyboard::isKeyPressed(sf::Keyboard::D) && player->rect.getPosition().x < ScW-playersize)
    player->rect.setPosition(player->rect.getPosition().x + millis*speedp, player->rect.getPosition().y);
if (sf::Keyboard::isKeyPressed(sf::Keyboard::A) && player->rect.getPosition().x > 0)
    player->rect.setPosition(player->rect.getPosition().x - millis*speedp, player->rect.getPosition().y);
}

void CGame::draw()
{
    window->draw(player->rect);
    for(unsigned int i = 0;i < bombs.size(); i++)
{
    window->draw(bombs[i]->rect);
}
    window->display();
    window->clear();
}

void CGame::throwBombs (int millis)
{
    counter += millis;
    if(counter >= 30 && bombs.size() <= 1000)
    {
    bombs.push_back(new CBomb(millis));
    counter = 0;
    }


for(unsigned int i = 0;i < bombs.size(); i++)
{
if(bombs[i]->rect.getPosition().y < 0)
{
    delete bombs[i];
    bombs.erase(bombs.begin()+i);
}


if(abs(player->rect.getPosition().x - bombs[i]->rect.getPosition().x) < (playersize+bombsize)/2 &&
   abs(player->rect.getPosition().y - bombs[i]->rect.getPosition().y) <  (playersize+bombsize)/2)
   restart();


bombs[i]->rect.setPosition(bombs[i]->rect.getPosition().x, bombs[i]->rect.getPosition().y - millis*speedb);

} // FOR
}

void CGame::restart()
{
        if(score > highscore)
    highscore = score;

    bombs.clear();
    player->rect.setPosition(ScW/2, 100);
    score = 0;
}


