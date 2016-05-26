package misc;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import core.Game;
import core.Screen;

public class KeyManager implements KeyListener
{
	public void keyPressed(KeyEvent ke)
	{
		if (ke.getKeyCode() == KeyEvent.VK_R) // if r is pressed
			Game.restartMap(); // restart

		if (ke.getKeyCode() == KeyEvent.VK_UP) // check for camera-movement-keys
			Screen.camYOffset -= Screen.CAM_OFFSET;
		else if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
			Screen.camXOffset += Screen.CAM_OFFSET;
		else if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			Screen.camYOffset += Screen.CAM_OFFSET;
		else if (ke.getKeyCode() == KeyEvent.VK_LEFT)
			Screen.camXOffset -= Screen.CAM_OFFSET;
		else if (ke.getKeyCode() == KeyEvent.VK_SPACE) // if space is pressed -> reset cam
		{
			Screen.camXOffset = 0;
			Screen.camYOffset = 0;
		}

		if (ke.getKeyCode() == KeyEvent.VK_W) // player movement
			Game.w = true;
		else if (ke.getKeyCode() == KeyEvent.VK_D)
			Game.d = true;
		else if (ke.getKeyCode() == KeyEvent.VK_S)
			Game.s = true;
		else if (ke.getKeyCode() == KeyEvent.VK_A)
			Game.a = true;

		
	}

	public void keyReleased(KeyEvent ke)
	{
		if (ke.getKeyCode() == KeyEvent.VK_W) // player movement
			Game.w = false;
		else if (ke.getKeyCode() == KeyEvent.VK_D)
			Game.d = false;
		else if (ke.getKeyCode() == KeyEvent.VK_S)
			Game.s = false;
		else if (ke.getKeyCode() == KeyEvent.VK_A)
			Game.a = false;
	}

	public void keyTyped(KeyEvent ke) {}
}
