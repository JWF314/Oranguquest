package com.joel.cpc.menu;

import com.joel.cpc.Game;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.input.KeyInput;

public class HelpMenu extends Menu {

	public HelpMenu(KeyInput input) {
		super(input);
	}
	
	int timer = 18;
	String options = "> Title Menu <";
	int selected = 0;
	
	public void tick() {
		if (timer > 0) timer--;
		
		if (selected == 0 && input.use && timer == 0) {
			input.releaseAll();
			Game.menu = new TitleMenu(input);
		}
	}
	
	public void render(Render render) {
		String[] text = { "Welcome to Oranguquest! So, I'm assuming you came here", //
			"to learn how to play this game. Well, here it is. You are a Zoo Keeper", //
			"who checks all the animals. Only to find that one day there is an", //
			"orangutan that has gone missing. It has escaped from its cage and is", //
			"hiding somewhere. The orangutan spawns in a random location on the", //
			"map and you must find it as your points go up. But wait? If they go up", //
			"that is good right? Wrong. This game is like golf. The lower the score", //
			"the better. The faster you find the orangutan, the better. So, find the", //
			"orangutan, save the zoo, and enjoy playing!", //
			" ", //
			"If any mobs spawn in rocks, tough luck, just press escape to restart." };
		
		for (int i = 0; i < text.length; i++) {
			render.drawText(text[i], 10 + 2, 80 + i * 30 + 2, 24, 0, 0);
			render.drawText(text[i], 10, 80 + i * 30, 24, 0, 0xffffff);
		}
		render.drawText(options, 15 + 3, 515 + 3, 50, 1, 0);
		render.drawText(options, 15, 515, 50, 1, 0xffffff);
	}
}
