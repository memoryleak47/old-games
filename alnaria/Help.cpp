#include "Game.hpp"

namespace help{
	int toOne(float f)
	{
		if (f == 0)
			return 0;
		else if (f > 0)
			return 1;
		else
			return -1;
	}

	sf::Vector2i toOneV(sf::Vector2f v)
	{
		return sf::Vector2i(toOne(v.x), toOne(v.y));
	}

	sf::Vector2i toOneV(sf::Vector2i v)
	{
		return sf::Vector2i(toOne(v.x), toOne(v.y));
	}
}

