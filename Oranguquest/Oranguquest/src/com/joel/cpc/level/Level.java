package com.joel.cpc.level;

import java.util.ArrayList;
import java.util.List;

import com.joel.cpc.entity.Entity;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.level.tile.Tile;

public class Level {
	
	public int width, height;
	public int[] tiles;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}
	
	protected void generateLevel() {
		
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	public void render(int xScroll, int yScroll, Render render) {
		render.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + render.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + render.height + 16) >> 4;
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, render);
			}
		}
		
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(render);
		}
	}
	
	public void add(Entity e) {
		entities.add(e);
		e.init(this);
	}
	
	public void add(Entity e, Level level) {
		entities.add(e);
		e.init(level);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return Tile.water;
		if (tiles[x + y * width] == 0) return Tile.flower;
		if (tiles[x + y * width] == 1) return Tile.rock;
		return Tile.grass;
	}
	
	public void killAll() {
		entities.clear();
	}

}
