package main;

import items.CoinItem;
import items.GravityItem;
import items.SpeedItem;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;

import base.Entity;

public class Game extends Canvas implements Runnable
{
	private static double FRAME_RATE = 40;
	static JFrame						frame;
	private static final long			serialVersionUID	= 1L;
	public static final int				WIDTH				= 300, HEIGHT = 200, SCALE = 1, DISPLAYSCALE = 4, TILESIZE = 12;
	public static boolean				running				= false;
	public Thread						gameThread;

	private static ImageLoader			loader;
	private static LinkedList<Entity>	entities;
	private static Level				level;

	public static int					itemCounter			= 0;

	public void init()
	{
		entities = new LinkedList<Entity>();
		loader = new ImageLoader();

		this.addKeyListener(new KeyManager());

		entities.add(new Player(990, 100, 1));
		entities.add(new Player(560, 150, 2));
		entities.add(new Player(100, 100, 3));

		level = new Level(loader.load("/level/CuC.png")); //TODa set back to /level/CuC.png
	}

	public synchronized void start()
	{
		if (running)
			return;

		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop()
	{
		if (!running)
			return;

		running = false;
		try
		{
			gameThread.join();
		} catch (InterruptedException e_)
		{
			e_.printStackTrace();
		}
	}

	public void run()
	{
		init();
		long _lastTime = System.nanoTime();
		double _ns = 1000000000 / FRAME_RATE;
		double _delta = 0;
		while (running)
		{
			long now = System.nanoTime();
			_delta += (now - _lastTime) / _ns;
			_lastTime = now;
			if (_delta >= 1)
			{
				tick();
				_delta--;
			}
			render();

		}
		stop();
	}

	public void tick()
	{
		if (level.solidTiles == null)
			return;

		String title = "";

		if (getPlayer1().runner == true)
		{
			title = "Cublock 3          GREEN: " + getPlayer1().score + " - Blue: " + getPlayer2().score + " - Red: " + getPlayer3().score + ".";
		} else
			if (getPlayer2().runner == true)
			{
				title = "Cublock 3          Green: " + getPlayer1().score + " - BLUE: " + getPlayer2().score + " - Red: " + getPlayer3().score + ".";
			} else
				if (getPlayer3().runner == true)
				{
					title = "Cublock 3          Green: " + getPlayer1().score + " - Blue: " + getPlayer2().score + " - RED: " + getPlayer3().score + ".";
				}
		frame.setTitle(title);

		createItems();
 		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).tick();
		}

		Entity.cameraOffsetX = 0;// (int) (getRunner().getOriginX() - Game.WIDTH
									// * DISPLAYSCALE / 2);
		Entity.cameraOffsetY = 0;// (int) (getRunner().getOriginY() -
									// Game.HEIGHT * DISPLAYSCALE / 2);
	}

	public void render()
	{
		BufferStrategy _bs = this.getBufferStrategy();
		if (_bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		Graphics _g = _bs.getDrawGraphics();
		// RENDER HERE
		_g.drawImage(ImageLoader.background, 0, 0, WIDTH * DISPLAYSCALE, HEIGHT * DISPLAYSCALE, null);
		level.render(_g);
		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(_g);
		}
		// END RENDER
		_g.dispose();
		_bs.show();
	}

	public static void main(String[] args)
	{
		Game _game = new Game();

		_game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		_game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		_game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame("CuBlock");
		frame.setSize(WIDTH * DISPLAYSCALE + 6, HEIGHT * DISPLAYSCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(_game);
		frame.setVisible(true);
		frame.setFocusable(true);
		_game.start();
	}

	void createItems()
	{
		if (Math.random() < 0.99f || itemCounter >= 10)
			return;
		
		float _x = (float) (Math.random() * level.getWidth() * Game.TILESIZE * Game.SCALE * 0.9f);
		float _y = (float) (Math.random() * level.getHeight() * Game.TILESIZE * Game.SCALE * 0.9f);
		switch ((int) (Math.random() * 3))
		{
			case 0:
			{
				Game.getEntities().add(new CoinItem(_x, _y, 10, 10));
				break;
			}
			case 1:
			{
				Game.getEntities().add(new GravityItem(_x, _y, 10, 10));
				break;
			}
			case 2:
			{
				Game.getEntities().add(new SpeedItem(_x, _y, 10, 10));
				break;
			}
			default:
			{
				break;
			}
		}
	}

	static public Player getPlayer1()
	{
		return (Player) entities.get(0);
	}

	static public Player getPlayer2()
	{
		return (Player) entities.get(1);
	}

	static public Player getPlayer3()
	{
		return (Player) entities.get(2);
	}

	static public Player getRunner()
	{
		if (getPlayer1().runner)
			return Game.getPlayer1();
		if (getPlayer2().runner)
			return Game.getPlayer2();
		if (getPlayer3().runner)
			return Game.getPlayer3();

		return null;
	}

	public static Level getLevel()
	{
		return level;
	}

	public static LinkedList<Entity> getEntities()
	{
		return entities;
	}

}
