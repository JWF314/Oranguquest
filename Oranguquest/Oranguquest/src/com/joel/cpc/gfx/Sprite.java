package com.joel.cpc.gfx;

public class Sprite {
	
	public static final int SIZE = 16;
	public int size;
	public int x, y;
	public int[] pixels;
	
	public static Sprite grass = new Sprite(0, 0, 16);
	public static Sprite flower = new Sprite(1, 0, 16);
	public static Sprite rock = new Sprite(2, 0, 16);
	public static Sprite water = new Sprite(8, 0, 16);
	public static Sprite sand = new Sprite(3, 0, 16);
	public static Sprite path = new Sprite(4, 0, 16);
	
	public static Sprite cactus_bottom = new Sprite(5, 0, 16);
	public static Sprite cactus_top = new Sprite(6, 0, 16);
	
	public static Sprite player_forward = new Sprite(2, 1, 32);
	public static Sprite player_back = new Sprite(0, 1, 32);
	public static Sprite player_left = new Sprite(2, 3, 32);
	public static Sprite player_right = new Sprite(0, 3, 32);
	
	public static Sprite plad_guy_back = new Sprite(4, 1, 32);
	public static Sprite plad_guy_forward = new Sprite(6, 1, 32);
	public static Sprite plad_guy_left = new Sprite(6, 3, 32);
	public static Sprite plad_guy_right = new Sprite(4, 3, 32);
	
	public static Sprite orangutan_back = new Sprite(0, 5, 32);
	
	public static Sprite voidSprite = new Sprite(0);
	
	public Sprite(int x, int y, int size) {
		this.size = size;
		pixels = new int[size * size];
		this.x = x << 4;
		this.y = y << 4;
		create(size);
	}
	
	public Sprite(int col) {
		pixels = new int[SIZE * SIZE];
		create(16, col);
	}
	
	public void create(int size) {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = SpriteSheet.pixels[(x + this.x) + (y + this.y) * 256];
			}
		}
	}
	
	public void create(int size, int col) {
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				pixels[x + y * size] = col;
			}
		}
	}
	
}
