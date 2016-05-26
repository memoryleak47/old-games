#include "CBomb.hpp"

CBomb::CBomb(int millis)
{
  //  srand(millis);
    rect.setSize(sf::Vector2f(bombsize, bombsize));
    rect.setFillColor(sf::Color::Red);
    rect.setPosition(rand() % ScW, ScH);
    rect.setOrigin(rect.getSize().x/2, rect.getSize().y/2);
}
