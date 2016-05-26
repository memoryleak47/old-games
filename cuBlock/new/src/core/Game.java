package core;

import items.CoinItem;
import items.GravityItem;
import items.SpeedItem;
import base.Entity;
import base.SolidTile;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{
	private static final double FRAME_RATE = 40;
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 300, HEIGHT = 200, SCALE = 1, DISPLAYSCALE = 4, TILESIZE = 12;
	
	public static boolean running = false;
	private Thread gameThread;
	private static JFrame frame;
	private static ImageLoader loader;
	private static LinkedList<Entity> entities;
	private static Level level;

	public static int itemCounter = 0;

	public void init()
	{
		entities = new LinkedList<Entity>();
		loader = new ImageLoader();

		this.addKeyListener(new KeyManager());

		entities.add(new Player(990, 300, 1));
		entities.add(new Player(540, 300, 2));
		entities.add(new Player(40, 300, 3));

		level = new Level(loader.load("/level/classic.png"));
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
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		double ns = 1000000000 / FRAME_RATE;
		double delta = 0;
		while (running)
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
		stop();
	}

	public void tick()
	{
		if (level.getSolidTiles() == null)
			return;

		String title = "";

		if (getPlayer1().runner == true)
		{
			title = "Cublock 3          RED: " + getPlayer1().score + " - Green: " + getPlayer2().score + " - Blue: " + getPlayer3().score + ".";
		} else
			if (getPlayer2().runner == true)
			{
				title = "Cublock 3          Red: " + getPlayer1().score + " - GREEN: " + getPlayer2().score + " - Blue: " + getPlayer3().score + ".";
			} else
				if (getPlayer3().runner == true)
				{
					title = "Cublock 3          Red: " + getPlayer1().score + " - Green: " + getPlayer2().score + " - BLUE: " + getPlayer3().score + ".";
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
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// RENDER HERE
		g.drawImage(ImageLoader.background, 0, 0, WIDTH * DISPLAYSCALE, HEIGHT * DISPLAYSCALE, null);
		level.render(g);
		for (int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(g);
		}
		// END RENDER
		g.dispose();
		bs.show();
	}

	public static void main(String[] args)
	{
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame("CuBlock");
		frame.setSize(WIDTH * DISPLAYSCALE + 6, HEIGHT * DISPLAYSCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(game);
		frame.setVisible(true);
		frame.setFocusable(true);
		game.start();
	}

	void createItems()
	{
		if (Math.random() < 0.99f || itemCounter >= 10)
			return;
		int x, y;
		do {
			x = (int) (Math.random() * level.getWidth());
			y = (int) (Math.random() * level.getHeight());
		} while (level.getSolidTiles()[x][y] != SolidTile.air); //TODO may consume memory

		x *= Game.SCALE * Game.TILESIZE;
		y *= Game.SCALE * Game.TILESIZE;

		switch ((int) (Math.random() * 3))
		{
			case 0:
			{
				Game.getEntities().add(new CoinItem(x, y));
				break;
			}
			case 1:
			{
				Game.getEntities().add(new GravityItem(x, y));
				break;
			}
			case 2:
			{
				Game.getEntities().add(new SpeedItem(x, y));
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
