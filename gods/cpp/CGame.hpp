#ifndef CGAME_HPP
#define CGAME_HPP

#include <SFML/Graphics.hpp>
#include <iostream>

#define ScH 600
#define ScW 800

class CPlayer;
class CArrow;
class CMissile;

class CGame
{
private:
    CGame();
    void onTick();
    void init();
    void uninit();
    CPlayer* player;

public:
        sf::RenderWindow* window;
    void run();
    static CGame& getInstance();

};

#include "CPlayer.hpp"
#include "CArrow.hpp"
#include "CMissile.hpp"

#endif // CGame


