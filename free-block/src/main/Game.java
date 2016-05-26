package main;

import javax.swing.JFrame;
import java.lang.Thread;
import java.awt.Canvas;
import java.awt.Dimension;
import java.util.LinkedList;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import base.Entity;
import cEntity.Player; //cEntity stands for child-classes of Entity
import other.action.KeyManager;
import other.graphic.ImageLoader;
import cEntity.cBlock.DamageBlock;
import other.math.Vec;
import cEntity.cBlock.SpecialBlock;
import cEntity.cBlock.cSpecialBlock.*;

public class Game extends Canvas implements Runnable
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final float STANDART_SPEEDX = 8;
	public static final int BORDER_HEIGHT = 100;
	public static final int TICK_DIFF = 60; //time after tick() is called again
	public static final int MIN_BLOCK_AMOUNT = 9;
	public static final int BLOCKSIZE = 32;
	public static final int DBLOCK_CONCENTRATION = 3;

	private float highscore = 0;
	private float score = 0;
	private JFrame frame;
	private LinkedList<Entity> entities;
	private Player player;
	static private Game game;
	private int dblockSpawnCounter = 0; //used for spawnin SpecialBlocks
	private int specialBlockNr = 0; // too

	private Game() //private -> no call from outside
	{
		frame = new JFrame("FreeBlock");
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //Window-size
		frame.add(this);
		frame.pack(); //uses Canvas.preferredSize to set size
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true); //needed for KeyListener
		this.addKeyListener(new KeyManager());
		restart();
		new Thread(this).start();
	}

	public static void main(String args[])
	{
		game = new Game();
	}

	public static Game get()
	{
		return game;
	}

	public void restart() //TODO real restart -> ERROR
	{
		if (score > highscore)
			highscore = score;
		score = 0;
		entities = new LinkedList<Entity>();
		entities.add(player = new Player());
	}

	public void run()
	{
		long lastTime = System.nanoTime();
		final double ns = 1000000000 / TICK_DIFF;
		double delta = 0;
		while (true)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				if (player.isDead())
					restart();
				else
					render();
				delta--;
			}
		}
	}

	private void tick()
	{
		manageBlocks();
		for (int i=0; i < getEntities().size(); i++)
		{
			getEntities().get(i).tick();
		}
		score += Game.get().getPlayer().getVel().getX();
	}

	private void manageBlocks()
	{
		if (getEntities().size() -1 < MIN_BLOCK_AMOUNT)
		{
			getEntities().add(new DamageBlock(
				new Vec(WIDTH-BLOCKSIZE+new Random().nextInt(800), new Random().nextInt(HEIGHT-BORDER_HEIGHT-BLOCKSIZE)+BORDER_HEIGHT),
				new Vec(BLOCKSIZE, BLOCKSIZE)));
			dblockSpawnCounter++;
			if (dblockSpawnCounter > DBLOCK_CONCENTRATION)
			{
				getEntities().add(addSpecialBlock(specialBlockNr));
				specialBlockNr++;
				if (specialBlockNr > 3)
					specialBlockNr = 0;
				dblockSpawnCounter = 0;
			}
		}
	}

	private SpecialBlock addSpecialBlock(int nr)
	{
		Vec pos = new Vec(WIDTH-BLOCKSIZE, new Random().nextInt(HEIGHT-BORDER_HEIGHT-BLOCKSIZE)+BORDER_HEIGHT);
		Vec size = new Vec(BLOCKSIZE, BLOCKSIZE);
		switch (nr)
		{
			case 0:
				return new RedBlock(pos, size);
			case 1:
				return new GreenBlock(pos, size);
			case 2:
				return new BlueBlock(pos, size);
			case 3:
				return new YellowBlock(pos, size);
			default:
				System.out.println("addSpecialBlock() returns null (nr == " + nr + ") -> should not go into entities");
				return null;
		}
	}

	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT); //reset background
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(new TexturePaint(ImageLoader.damageBlock, new Rectangle(0, 0, BLOCKSIZE, BLOCKSIZE)));
		g2d.fill(new Rectangle2D.Float(0, 0, WIDTH, BORDER_HEIGHT));
		g2d.fill(new Rectangle2D.Float(0, HEIGHT - BORDER_HEIGHT, WIDTH, BORDER_HEIGHT));
		g.setColor(Color.BLACK);
		g.fillRect(20, 10, 300, 50);
		g.setColor(Color.WHITE);
		g.drawString("SCORE: " + (int) score, 30, 30);
		g.drawString("HIGH:  " + (int) highscore, 30, 50);
		//TODO correct above
		//RENDER HERE
		for (int i = 0; i < getEntities().size(); i++)
		{
			g.drawImage(
			getEntities().get(i).getImage(),
				(int) getEntities().get(i).getPos().getX(),
				(int) getEntities().get(i).getPos().getY(),
				(int) getEntities().get(i).getSize().getX(),
				(int) getEntities().get(i).getSize().getY(),
				null);
		}
		//END RENDER
		g.dispose();
		bs.show();
	}

	public void addScore(float f)
	{
		score += f;
	}

	public LinkedList<Entity> getEntities()
	{
		return entities;
	}

	public Player getPlayer()
	{
		return player; //faster; but unsafe: return getEntities().get(0);
	}
}
