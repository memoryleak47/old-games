#include "CArrow.hpp"

CArrow::CArrow(sf::Vector2f positionArg, sf::Vector2f velocityArg) : CMissile(positionArg, velocityArg)
{
    aimEnimies = true;
    sprite.setScale(sf::Vector2f(1, 1));
        sprite.setOrigin(sprite.getScale().x/2, sprite.getScale().y/2);

    if (!texture.loadFromFile("Arrow.png"))
    {
        std::cerr << "TextureArrow";
    }
        sprite.setTexture(texture);
}

