#ifndef CMISSILE_HPP
#define CMISSILE_HPP

//#include "CGame.hpp"
#include <SFML/Graphics.hpp>

class CMissile
{
public:
    sf::Sprite sprite;
    CMissile(sf::Vector2f positionArg, sf::Vector2f velocityArg);
    void update();
    void move();
    bool checkDeath();


protected:
    bool aimEnimies;
    sf::Texture texture;
    sf::Vector2f velocity;
};

#endif // CMISSILE_HPP
