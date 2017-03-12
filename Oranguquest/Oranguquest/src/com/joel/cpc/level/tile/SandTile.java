package com.joel.cpc.level.tile;

import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.Sprite;

public class SandTile extends Tile {
	
	public SandTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Render render) {
		render.drawTile(x << 4, y << 4, this);
	}

}
