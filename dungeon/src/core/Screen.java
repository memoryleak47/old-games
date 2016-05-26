package core;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;

import core.Game;
import misc.KeyManager;

public class Screen extends JPanel
{
	public static final int CAM_OFFSET = 16;
	public static int camXOffset = 0, camYOffset = 0;
	private static JFrame frame;
	private static Screen instance = new Screen(); 
	private static BufferedImage buffer;
	private static Graphics g;

	private Screen()
	{
		// init stuff
		frame = new JFrame("Dungeon");
		frame.add(this);
		frame.setSize(Game.SW, Game.SH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		buffer = new BufferedImage(Game.SW, Game.SH, BufferedImage.TYPE_INT_ARGB);
		g = buffer.getGraphics();
		frame.setFocusable(true);
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(new KeyManager());
	}

	static void update()
	{
		instance.getGraphics().drawImage(buffer, 0, 0, Game.SW, Game.SH, null); // renders buffer on screen
		g.dispose(); // disposes g
		g = buffer.getGraphics(); // setups g (used in Screen.draw(...))
		g.setColor(Color.BLACK); // clears ...
		g.fillRect(0, 0, Game.SW, Game.SH); // ... screen
	}

	static void tick()
	{
		if (Game.w || Game.s || Game.a || Game.d) // if move-keys are pressed
			camXOffset = camYOffset = 0; // reset cam
	}

	private static int getCamX() // complicated function to get left border of screen/cam
	{
		return (int) -(camXOffset + Game.getPlayer().x()*Game.TILESIZE - Game.SW/2);
	}

	private static int getCamY()
	{
		return (int) -(camYOffset + Game.getPlayer().y()*Game.TILESIZE - Game.SH/2);
	}

	public static void draw(BufferedImage image, int x, int y) // draws image on buffer using cam-position
	{
		g.drawImage(image, x+getCamX(), y+getCamY(), Game.TILESIZE, Game.TILESIZE, null);
	}

	public static void draw(BufferedImage image, int x, int y, int sizeX, int sizeY)
	{
		g.drawImage(image, x+getCamX(), y+getCamY(), sizeX, sizeY, null);
	}

	public static Screen get() { return instance; }
}
