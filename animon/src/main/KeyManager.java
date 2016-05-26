package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyManager implements KeyListener{
	public static boolean up, dn, rt, lt, q, w, e, r;
	
	public void keyPressed(KeyEvent event) {
	    
		if (event.getKeyCode() == KeyEvent.VK_UP)
			up = true;
		
		if (event.getKeyCode() == KeyEvent.VK_DOWN)
			dn = true;
		
		if (event.getKeyCode() == KeyEvent.VK_RIGHT)
			rt = true;
		
		if (event.getKeyCode() == KeyEvent.VK_LEFT)
			lt = true;
		
	if(event.getKeyCode() == KeyEvent.VK_Q)q = true;
	if(event.getKeyCode() == KeyEvent.VK_W)w = true;
	if(event.getKeyCode() == KeyEvent.VK_E)e = true;
	if(event.getKeyCode() == KeyEvent.VK_R)r = true;
			
	}

	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP)
			up = false;
		
		if (event.getKeyCode() == KeyEvent.VK_DOWN)
			dn = false;
		
		if (event.getKeyCode() == KeyEvent.VK_RIGHT)
			rt = false;
		
		if (event.getKeyCode() == KeyEvent.VK_LEFT)
			lt = false;	
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
