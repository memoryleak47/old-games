#include "CPlayer.hpp"

CPlayer::CPlayer()
{
    rect.setSize(sf::Vector2f(playersize, playersize));
    rect.setFillColor(sf::Color::White);
    rect.setPosition(ScW/2, 100);
    rect.setOrigin(rect.getSize().x/2, rect.getSize().y/2);
}
