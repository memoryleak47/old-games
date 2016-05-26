#ifndef CGAME_HPP
#define CGAME_HPP

#include <SFML/Graphics.hpp>
//#include <vector>
#include <iostream>
//#include <cstdlib>
//#include <fstream>

#define ScH 600
#define ScW 800
#define playersize 25
#define bombsize 10
#define speedp 0.4f
#define speedb 0.4f

class CPlayer;
class CBomb;

class CGame
{
private:
    void draw();
    std::vector<CBomb*> bombs;
    void walk(int millis);
    void throwBombs(int millis);
    int counter;
    sf::RenderWindow* window;
    CPlayer* player;
    int highscore;
    int score;
    sf::Clock clock;
    bool active;
    void restart();
public:
    CGame();
    static CGame& getInstance();
    void onTick(int millis);
    void run();


};

#include "CPlayer.hpp"
#include "CBomb.hpp"

#endif // CGame
