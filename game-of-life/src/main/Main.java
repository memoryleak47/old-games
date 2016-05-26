package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.LinkedList;
import java.awt.event.*;

public class Main extends Canvas
{
	public static final String MODE = "";
	public static final int SCREENWIDTH = 800, SCREENHEIGHT = 600, CURVE_DIV = 100;
	public static int WIDTH, HEIGHT;
	public static int MIN_SPAWN = 3, MAX_DIE_LONELY = 1, MIN_DIE_OVERPOP = 4, CURVE_WAIT = 2, ZOOM = 4;
	public static double START_SPAWN_POSSIBILITY = 0.5D; 
	boolean[][] pixels;
	JFrame frame;
	LinkedList<Integer> curve;

	public static void main(String args[])
	{
		if (args.length > 0)
		{
			if (args[0].equals("help"))
			{
				System.out.println("args: ZOOM MIN_SPAWN MAX_DIE_LONELY MIN_DIE_OVERPOP CURVE_WAIT START_SPAWN_POSSIBILITY");
				System.exit(0);
			}
			ZOOM = Integer.parseInt(args[0]);
		}
		if (args.length > 1)
			MIN_SPAWN = Integer.parseInt(args[1]);
		if (args.length > 2)
			MAX_DIE_LONELY = Integer.parseInt(args[2]);
		if (args.length > 3)
			MIN_DIE_OVERPOP = Integer.parseInt(args[3]);
		if (args.length > 4)
			CURVE_WAIT = Integer.parseInt(args[4]);
		if (args.length > 5)
			START_SPAWN_POSSIBILITY = Double.parseDouble(args[5]);
		if (args.length > 6)
		{
			System.out.println("args: ZOOM MIN_SPAWN MAX_DIE_LONELY MIN_DIE_OVERPOP CURVE_WAIT START_SPAWN_POSSIBILITY");
			System.exit(0);
		}
		WIDTH = SCREENWIDTH/ZOOM;
		HEIGHT = SCREENHEIGHT/ZOOM;
		new Main();
	}

	public Main()
	{
		frame = new JFrame("Game of life");
		setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyManager(this));
		pixels = new boolean[WIDTH][HEIGHT];
		curve = new LinkedList<Integer>();

		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				set(i, j, false);

		init();
		if (MODE.equals("KB"))
			return;
		long lastTime = System.nanoTime();
		final double ns = 1000000000 / 60;
		double delta = 0;
		while (true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				if (MODE.equals("SLEEP"))
					try{Thread.sleep(1000);} catch (Exception e) {}
				tick();
				render();
				delta--;
			}
		}
		
	}

	public void init()
	{

		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				set(i, j, Math.random() < START_SPAWN_POSSIBILITY);
	
/*	set(100, 100, true);
	set(100, 101, true);
	set(100, 102, true);
	set(101, 102, true);
	set(102, 101, true);
/*
  XX
 XX X
 X
  X
*//*

		set(101, 100, true);
		set(102, 100, true);
		set(100, 101, true);
		set(101, 101, true);
		set(100, 102, true);
		set(103, 101, true);
*/
	}

	public int countPixels()
	{
		int c = 0; for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				if (at(i, j))
					c++;


		return c;
	}

	public void set(int x, int y, boolean val)
	{
		pixels[x][y] = val;
		//System.out.println("pixels[" + x + "][" + y + "] = " + val);
	}

	public boolean at(int x, int y)
	{
		return pixels[x][y];
	}

	public int countAround(int x, int y)
	{
		int c = 0;
		for (int i = x-1; i <= x+1; i++)
			for (int j = y-1; j <= y+1; j++)
			{
				if ((i == x && j == y) || i < 0 || i >= WIDTH || j < 0 || j >= HEIGHT || !at(i, j))
					continue;
				c++;
			}
		return c;
	}

	public void tick()
	{
		boolean[][] tmp = new boolean[WIDTH][HEIGHT];

		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				tmp[i][j] = at(i, j);

		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
			{
				if (!at(i, j))
				{
					if (countAround(i, j) >= MIN_SPAWN && countAround(i, j) < MIN_DIE_OVERPOP)
						tmp[i][j] = true;
				} else
				{
					if (countAround(i, j) >= MIN_DIE_OVERPOP)
						tmp[i][j] = false;
					else if (countAround(i, j) <= MAX_DIE_LONELY)
						tmp[i][j] = false;
				}
			}
		
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				set(i, j, tmp[i][j]);

		frame.setTitle("Game of life (" + countPixels() + ")");

		curve.add(countPixels()/CURVE_DIV);
		while (curve.size() > SCREENWIDTH)
			curve.remove();
	}

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT); //reset background
		g.setColor(Color.GREEN);

		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				if (at(i, j))
					g.fillRect(i*ZOOM, j*ZOOM, ZOOM, ZOOM);

		g.setColor(Color.RED);
		for (int i = 0; i < curve.size(); i++)
		{
			g.fillRect(i, SCREENHEIGHT-(curve.get(i)%SCREENHEIGHT), 2, 2);
		}
		g.dispose();
		bs.show();
		
	}
}

class KeyManager implements KeyListener
{
	Main m;
	public KeyManager(Main m)
	{
		this.m = m;
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_Q)
			System.exit(0);
		if (! m.MODE.equals("KB"))
			return;
		m.tick();
		m.render();
	}

	public void keyReleased(KeyEvent e)
	{}

	public void keyTyped(KeyEvent e)
	{}
}
