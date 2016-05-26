package main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.LinkedList;

import main.Pixel;

public class Main extends Canvas
{
	public static final int WIDTH = 800, HEIGHT = 600;
	public static int START_AMOUNT = 2;
	JFrame frame;

	public static void main(String args[])
	{
		START_AMOUNT = 2;
		Pixel.SIZE = 10;
		Pixel.MAX_COLLS = 3;
		Pixel.MAX_VEL = 1;
		Pixel.CURVE_WAIT = 4;

		if (args.length > 0)
			START_AMOUNT = Integer.parseInt(args[0]);
		if (args.length > 1)
			Pixel.SIZE = Integer.parseInt(args[1]);
		if (args.length > 2)
			Pixel.MAX_COLLS = Integer.parseInt(args[2]);
		if (args.length > 3)
			Pixel.MAX_VEL = Double.parseDouble(args[3]);
		if (args.length > 4)
			Pixel.CURVE_WAIT = Integer.parseInt(args[4]);
		if (args.length > 5)
			System.out.println("too many args. Usage: ... [start_amount[size[max_coll[speed[curve_wait]]]]]");
		Pixel.init();
		new Main();
	}

	public Main()
	{
		frame = new JFrame("Plancton");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				tick();
				render();
				delta--;
			}
		}
		
	}

	public void tick()
	{
		Pixel.tick();
		frame.setTitle("Plancton (" + Pixel.pixels.size() + ")");
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
		g.fillRect(0, 0, WIDTH, HEIGHT); //reset background
		g.setColor(Color.BLUE);

		for (Pixel pixel : Pixel.pixels)
		{
			g.fillRect((int)(pixel.x-pixel.SIZE/2), (int)(pixel.y-pixel.SIZE/2), (int)pixel.SIZE, (int)pixel.SIZE);
		}
		g.setColor(Color.RED);
		for (int i = 0; i < Pixel.curve.size(); i++)
		{
			g.fillRect(i, HEIGHT-(Pixel.curve.get(i)%HEIGHT), 2, 2);
		}
		g.dispose();
		bs.show();
		
	}
}
