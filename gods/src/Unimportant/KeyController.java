package Unimportant;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Important.Game;
import Important.Player;

public class KeyController implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_A:
		{
			Game.getPlayer();
			Player.left = true;
			break;
		}
		case KeyEvent.VK_D:
		{
			Game.getPlayer();
			Player.right = true;
			break;
		}
		case KeyEvent.VK_W:
		{
			Game.getPlayer();
			Player.up = true;
			break;
		}
		case KeyEvent.VK_S:
		{
			Game.getPlayer();
			Player.down = true;
			break;
		}
		case KeyEvent.VK_SPACE:
		{
			Game.getPlayer();
			Player.space = true;
			break;
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_A:
		{
			Game.getPlayer();
			Player.left = false;
			break;
		}
		case KeyEvent.VK_D:
		{
			Game.getPlayer();
			Player.right = false;
			break;
		}
		case KeyEvent.VK_W:
		{
			Game.getPlayer();
			Player.up = false;
			break;
		}
		case KeyEvent.VK_S:
		{
			Game.getPlayer();
			Player.down = false;
			break;
		}
		case KeyEvent.VK_SPACE:
		{
			Game.getPlayer();
			Player.space = false;
			break;
		}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
