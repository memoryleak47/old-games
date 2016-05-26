package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class  KeyController implements KeyListener {
	

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_D:
		{
			Game.getPlayer().right = true;
			break;
		}
		case KeyEvent.VK_A:
		{
			Game.getPlayer().left = true;
			break;
		}
		case KeyEvent.VK_W:
		{
			Game.getPlayer().up = true;
			break;
		}
		case KeyEvent.VK_S:
		{
			Game.getPlayer().down = true;
			break;
		}
		case KeyEvent.VK_Q:
		{
			Game.getPlayer().left = true;
			break;
		}
		case KeyEvent.VK_SPACE:
		{
			Game.getPlayer().jump = true;
		}
		}
	}

	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_D:
		{
			Game.getPlayer().right = false;
			break;
		}
		case KeyEvent.VK_A:
		{
			Game.getPlayer().left = false;
			break;
		}
		case KeyEvent.VK_W:
		{
			Game.getPlayer().up = false;
			break;
		}
		case KeyEvent.VK_S:
		{
			Game.getPlayer().down = false;
			break;
		}
		case KeyEvent.VK_Q:
		{
			Game.getPlayer().left = false;
			break;
		}
		case KeyEvent.VK_SPACE:
		{
			Game.getPlayer().jump = false;
		}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
