package main;

import graphic.ImageLoader;
import level.Level;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {


	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 200, HEIGHT = 200, SCALE = 4, TILESIZE = 30;
	public static boolean running = false;
	public Thread gameThread;

	private static Player player;

	private static Level level;

	private static ImageLoader loader;

	public void init() {
		loader = new ImageLoader();
		BufferedImage levelImage;
		levelImage = loader.load("/level/level1.png");
		level = new Level(levelImage);
		player = new Player(300, 300, loader.playerStand);

		this.addKeyListener(new KeyController());
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
				render(); //move from :74 to here
				delta--;
			}
		}
		stop();
	}

	public void tick() {
		player.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		// RENDER HERE
		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
		level.render(g);
		player.render(g);
		// END RENDER
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame("PotatoBird");
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();
		game.start();
	}

	public static Player getPlayer() {
		return player;
	}

	public static Level getLevel() {
		return level;
	}

	public static ImageLoader getLoader() {
		return loader;
	}

}
