#include "CPlayer.hpp"

CPlayer::CPlayer()
{
    init();
}

void CPlayer::init()
{
    cd = 0;
    arrows.clear();

    velocity = sf::Vector2f(0, 0);
    if (!texture.loadFromFile("Player.png"))
    {
        std::cerr << "TexturePlayer";
    }
    sprite.setPosition(ScW/2, ScH/2);
    sprite.setTexture(texture);
    sprite.setScale(sf::Vector2f(2, 2));
    sprite.setOrigin(sprite.getScale().x/2, sprite.getScale().y/2);
}

void CPlayer::update()
{
    input();
    move();
    checkArrows();

    if (cd > 0)
        cd--;
}

void CPlayer::move()
{
    float antiDrag = 10;

    velocity -= sf::Vector2f(velocity.x/antiDrag, velocity.y/antiDrag);
    sprite.setPosition(sprite.getPosition() + velocity);
}

void CPlayer::input()
{
    float acc = 0.075f;

    if(sf::Keyboard::isKeyPressed(sf::Keyboard::W))
    {
        velocity += sf::Vector2f(0, -acc);
    }
    if(sf::Keyboard::isKeyPressed(sf::Keyboard::S))
    {
        velocity += sf::Vector2f(0, acc);
    }
    if(sf::Keyboard::isKeyPressed(sf::Keyboard::A))
    {
        velocity += sf::Vector2f(-acc, 0);
    }
    if(sf::Keyboard::isKeyPressed(sf::Keyboard::D))
    {
        velocity += sf::Vector2f(acc, 0);
    }
    if(sf::Keyboard::isKeyPressed(sf::Keyboard::Space) && cd <= 0)
    {
        shoot();
    }
}

void CPlayer::shoot()
{
    sf::Vector2i Mouse = sf::Mouse::getPosition();
    float mouseX = Mouse.x - 120;
    float mouseY = Mouse.y - 115;
    sf::Vector2f mouse = sf::Vector2f(mouseX, mouseY);
    sf::Vector2f toMouse = mouse - sprite.getPosition();

    arrows.push_back(new CArrow(sprite.getPosition(), toMouse));
    cd = 200;
}

void CPlayer::checkArrows()
{
    for (int i = 0; i < arrows.size(); i++)
    {
     if (arrows[i]->checkDeath())
     {
        delete arrows[i];
     arrows.erase(arrows.begin()+i);
     }
    }
}
