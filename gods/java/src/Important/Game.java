package Important;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;

import BaseClasses.Enemy;
import BaseClasses.Missile;
import BaseClasses.Splatter;
import Enemies.Ghost;
import Enemies.Mage;
import Enemies.Sun;
import Missiles.PlayerMissile;
import Unimportant.ImageLoader;
import Unimportant.KeyController;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 300, SCALE = 2;
	public static boolean running = false;
	public Thread gameThread;
	private static int level;

	static private Player player;
	static private ImageLoader loader;
	static private LinkedList<Missile> missiles;
	static private LinkedList<Enemy> enemies;
	static private LinkedList<Splatter> splatters;

	public void init() {
		enemies = new LinkedList<Enemy>();
		missiles = new LinkedList<Missile>();
		splatters = new LinkedList<Splatter>();

		player = new Player();
		loader = new ImageLoader();

		addKeyListener(new KeyController());
		level = 1;

		addEnemy();
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60D;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				delta--;
			}
			render();

		}
		stop();
	}

	public void tick() {
		player.tick();

		if (player.isDead()) {
			restart();
		}

		for (int i = 0; i < missiles.size(); i++) {
			missiles.get(i).tick();

			if (missiles.get(i).isDead())
				missiles.remove(i);
		}

		for (int j = 0; j < enemies.size(); j++) {
			enemies.get(j).tick();

			if (enemies.get(j).isDead()) {
				enemies.remove(j);
				addEnemy();

			}

			for (int i = 0; i < splatters.size(); i++) {
				splatters.get(i).tick();

				if (splatters.get(i).isDead())
					splatters.remove(i);
			}
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// RENDER HERE
		g.drawImage(ImageLoader.backgroundImage, 0, 0, WIDTH * SCALE, HEIGHT
				* SCALE, null);

		for (int j = 0; j < enemies.size(); j++)
			enemies.get(j).render(g);

		Player.render(g);
		for (int i = 0; i < missiles.size(); i++)
			missiles.get(i).render(g);

		for (int j = 0; j < splatters.size(); j++)
			splatters.get(j).render(g);

		// END RENDER
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame("TheGods");
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
		frame.setFocusable(true);

		game.start();
	}

	static public Player getPlayer() {
		return player;
	}

	static public ImageLoader getLoader() {
		return loader;
	}

	static public void addPlayerMissile() {
		PointerInfo info = MouseInfo.getPointerInfo();
		Point loc = info.getLocation();
		float direcX = (loc.x - 8) - player.getX();
		float direcY = (loc.y - 30) - player.getY();
		missiles.add(new PlayerMissile(player.getX(), player.getY(), direcX,
				direcY));
	}

	static public void addEnemy() {
		switch (level) {
		case 1: {
			enemies.add(new Sun());
			break;
		}
		case 2: {
			enemies.add(new Ghost());
			break;
		}
		case 3: {
			enemies.add(new Mage());
			break;
		}

		}

		level++;

	}

	static public LinkedList<Missile> getMissiles() {
		return missiles;
	}

	static public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	static public LinkedList<Splatter> getSplatters() {
		return splatters;
	}

	void restart() {
		level = 1;
		player = new Player();
		enemies.clear();
		missiles.clear();
		addEnemy();
	}

}
