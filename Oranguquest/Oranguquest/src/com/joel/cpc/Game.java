package com.joel.cpc;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.joel.cpc.entity.mob.Orangutan;
import com.joel.cpc.entity.mob.PladGuy;
import com.joel.cpc.entity.mob.Player;
import com.joel.cpc.entity.mob.Visiter;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.SpriteSheet;
import com.joel.cpc.input.KeyInput;
import com.joel.cpc.level.Level;
import com.joel.cpc.level.RandomLevel;
import com.joel.cpc.menu.Menu;
import com.joel.cpc.menu.PlayMenu;
import com.joel.cpc.menu.TitleMenu;
import com.joel.cpc.sound.Sound;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = WIDTH / 16 * 10;
	private static final int SCALE = 3;
	
	private static final String TITLE = "Oranguquest";
	private static String version  = "Alpha 0.1.1";
	
	private Thread thread;
	private JFrame frame;
	private int time = 0;
	private boolean running = false;
	private BufferedImage img;
	private int[] pixels;
	private Render render;
	public static Menu menu;
	public static Level level;
	private static Player player;
	private static KeyInput key;
	public static int score = 0;
	private Random rand = new Random();
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		
		img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
		
		frame = new JFrame();
		render = new Render(WIDTH, HEIGHT);
		new SpriteSheet("/sheet/spritesheet.png");
		key = new KeyInput();
		setMenuLevel();
		menu = new TitleMenu(key);
		
		addKeyListener(key);
	}
	
	public static void setMenuLevel() {
		//Sound.test.play(false);
		level = new RandomLevel(512, 512);
		player = new Player(key);
		for (int i = 0; i < 5; i++) {
			level.add(new Visiter());
		}
		for (int i = 0; i < 2; i++) {
			level.add(new Orangutan());
		}
		for (int i = 0; i < 5; i++) {
			level.add(new PladGuy());
		}
	}
	
	public synchronized void start() {
		if (running) return;
		running = true;
		thread = new Thread(this, "MainThread");
		thread.start();
	}
	
	public synchronized void stop() {
		Sound.stopAll();
		if (!running) return;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long lastTimer = System.currentTimeMillis();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {	
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			while (System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println(frames + " FPS, " + updates + " UPS");
				frames = 0;
				updates = 0;
				if (menu == null) score += 2;
			}
		}
	}
	
	public void tick() {
		time++;
		if (time > 65536) time = 0;
		key.tick();
		if (menu != null) menu.tick();
		level.tick();
		
		if (PlayMenu.biome == 0) {
			level = new RandomLevel(128, 128);
			Visiter v = new Visiter();
			Orangutan o = new Orangutan();
			PladGuy p = new PladGuy();
			level.add(player, level);			
			level.add(v, level);
			level.add(o, level);
			level.add(p, level);
			player.x = 170;
			player.y = 130;
			v.x = 150;
			v.y = 110;
			p.x = 150;
			p.y = 150;
			o.x = rand.nextInt(44 * 44);
			o.y = rand.nextInt(44 * 44);
			PlayMenu.biome = -1;
		}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		render.graphics(g);
		render.clear();
		
		int xScroll = player.x - render.width / 2;
		int yScroll = player.y - render.height / 2;
		
		level.render(xScroll, yScroll, render);
		
		for (int i = 0; i < WIDTH * HEIGHT; i++) {
			pixels[i] = render.pixels[i];
		}
		
		g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		if (menu != null) {
			menu.render(render);
			Font font = new Font("Verdana", 0, 20);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("Oranguquest Alpha 0.1.0", 640 + 1, 525 + 1);
			g.setColor(Color.white);
			g.drawString("Oranguquest Alpha 0.1.0", 640, 525);
		}
		
		render.drawText(version, 15 + 2, 35 + 2, 40, 0, 0);
		render.drawText(version, 15, 35, 40, 0, 0xffffff);
		
		if (menu == null) {
			render.drawText(score + " Points", 15 + 2, 75 + 2, 30, 0, 0);
			render.drawText(score + " Points", 15, 75, 30, 0, 0xffffff);
		}
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(true);
		game.frame.setTitle(TITLE);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

}
