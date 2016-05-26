package main;
import helps.Vi2;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;

import javax.swing.JFrame;

import level.Level;
import abilityMain.Ability;
import animonMain.AnimonController;
import animonMain.CharacterController;
import animons.Nudelmon;
import animons.Player;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 200, HEIGHT = 200, SCALE = 4,
			TILESIZE = 16;
	public static boolean running = false;
	public Thread gameThread;
	
	private static ImageLoader loader;

	private static Player player;
	private static LinkedList<AnimonController> animons;
	private static LinkedList<Ability> activeAbilities;
	private static Level level;
	

	public void init() {

		loader = new ImageLoader();
		player = new Player(new Vi2(WIDTH*SCALE/2, HEIGHT*SCALE/2 -32));

		
		this.addKeyListener(new KeyManager());
		level = new Level(loader.load("/level/level1.png"));
		
		activeAbilities = new LinkedList<Ability>();
animons = new LinkedList<AnimonController>();
animons.add(new Nudelmon(new Vi2(1, 1)));
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
				render();
				delta--;
			}

		}
		stop();
	}

	public void tick() {
	    
		player.tick();
		for(int i = 0; i < animons.size(); i++){animons.get(i).tick();}
		for(int i = 0; i < activeAbilities.size(); i++){
		    if(activeAbilities.get(i).active == true)
			activeAbilities.get(i).tick();}
		
		    Entity.cameraOffset = player.getOrigin().getAdd(-400);
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
		
		for(int i = 0; i < animons.size(); i++){animons.get(i).render(g);}
		for(int i = 0; i < activeAbilities.size(); i++){
		    if(activeAbilities.get(i).active == true)
			activeAbilities.get(i).render(g);}
		// END RENDER
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame("Animon");
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);

		game.start();
	}

	static public Player getPlayer() {
		return player;
	}
	
	public static ImageLoader getLoader()
	{
		return loader;
	}
	
	public static Level getLevel()
	{
		return level;
	}
	
	public static LinkedList<AnimonController> getAnimons()
	{
		return animons;	
	}
	
	public static LinkedList<Ability> getActiveAbilities()
	{
		return activeAbilities;
	}
	
	public static LinkedList<CharacterController> getLivingObjects()
	{
		LinkedList<CharacterController> temp = new LinkedList<CharacterController>();
		temp.add(player);
		for (int i = 0; i < animons.size(); i++)
		{
			temp.add(animons.get(i));
		}
		return temp;
	}
	
}
