package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{

	public void keyPressed(KeyEvent event_)
	{

		if (event_.getKeyCode() == KeyEvent.VK_UP)
			Game.getPlayer1().up = true;
		if (event_.getKeyCode() == KeyEvent.VK_RIGHT)
			Game.getPlayer1().rt = true;
		if (event_.getKeyCode() == KeyEvent.VK_LEFT)
			Game.getPlayer1().lt = true;

		if (event_.getKeyCode() == KeyEvent.VK_W)
			Game.getPlayer2().up = true;
		if (event_.getKeyCode() == KeyEvent.VK_A)
			Game.getPlayer2().lt = true;
		if (event_.getKeyCode() == KeyEvent.VK_D)
			Game.getPlayer2().rt = true;

		if (event_.getKeyCode() == KeyEvent.VK_I)
			Game.getPlayer3().up = true;
		if (event_.getKeyCode() == KeyEvent.VK_J)
			Game.getPlayer3().lt = true;
		if (event_.getKeyCode() == KeyEvent.VK_L)
			Game.getPlayer3().rt = true;

	}

	public void keyReleased(KeyEvent event_)
	{
		if (event_.getKeyCode() == KeyEvent.VK_UP)
			Game.getPlayer1().up = false;
		if (event_.getKeyCode() == KeyEvent.VK_RIGHT)
			Game.getPlayer1().rt = false;
		if (event_.getKeyCode() == KeyEvent.VK_LEFT)
			Game.getPlayer1().lt = false;

		if (event_.getKeyCode() == KeyEvent.VK_W)
			Game.getPlayer2().up = false;
		if (event_.getKeyCode() == KeyEvent.VK_A)
			Game.getPlayer2().lt = false;
		if (event_.getKeyCode() == KeyEvent.VK_D)
			Game.getPlayer2().rt = false;

		if (event_.getKeyCode() == KeyEvent.VK_I)
			Game.getPlayer3().up = false;
		if (event_.getKeyCode() == KeyEvent.VK_J)
			Game.getPlayer3().lt = false;
		if (event_.getKeyCode() == KeyEvent.VK_L)
			Game.getPlayer3().rt = false;

	}

	public void keyTyped(KeyEvent event_)
	{

	}

}
