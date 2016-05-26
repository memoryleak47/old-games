#include "CMissile.hpp"

CMissile::CMissile(sf::Vector2f positionArg, sf::Vector2f velocityArg)
{
    velocity = velocityArg;
    velocity = sf::Vector2f(velocity.x/100, velocity.y/100);
    sprite.setPosition(positionArg);
  //  sprite.setRotation(velocityArg.x);
}

void CMissile::update()
{
    move();
    checkDeath();
}

void CMissile::move()
{
sprite.setPosition(sprite.getPosition() + velocity);
}

bool CMissile::checkDeath()
{
 if (sprite.getPosition().x > 1000 ||
     sprite.getPosition().x < 0 ||
     sprite.getPosition().y > 1000 ||
     sprite.getPosition().y < 0)
     return true;
     else
        false;
}
