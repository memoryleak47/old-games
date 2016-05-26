#ifndef CARROW_HPP
#define CARROW_HPP

#include "CGame.hpp"
#include "CMissile.hpp"

class CArrow : public CMissile
{
public:
    CArrow(sf::Vector2f positionArg, sf::Vector2f velocityArg);

};


#endif //CARROW_HPP
