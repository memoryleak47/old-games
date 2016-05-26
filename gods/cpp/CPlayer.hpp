#ifndef CPLAYER_HPP
#define CPLAYER_HPP

#include "CGame.hpp"

class CPlayer
{
public:
    CPlayer();
    void init();
    void update();
    void move();
    void input();
    sf::Sprite sprite;
    void shoot();
        std::vector<CArrow*> arrows;
        void checkArrows();

private:
    sf::Texture texture;
    sf::Vector2f velocity;
    int cd;
};

#endif // CPLAYER_HPP
