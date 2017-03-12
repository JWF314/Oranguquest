package com.joel.cpc.menu;

import com.joel.cpc.Game;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.input.KeyInput;

public class AboutMenu2 extends Menu {
	
	public AboutMenu2(KeyInput input) {
		super(input);
	}
	
	int timer = 18;
	String options = "> Previous Page <";
	int selected = 0;
	
	public void tick() {
		if (timer > 0) timer--;
		
		if (selected == 0 && input.use && timer == 0) {
			Game.menu = new AboutMenu(input);
		}
		if (input.back) Game.menu = new TitleMenu(input);
	}
	
	public void render(Render render) {
		String[] text = { "So, me and my cousin Chesapeake were driving home from", //
			"the park, and we were talking about orangutans. And then we", //
			"figured we could make a game about orangutans. And then Chesapeake", //
			"had an idea. It was basically this game exactly. He drew the graphics", //
			"(I drew some as well)", //
			"while I wrote every line of code including this so you can read what I", //
			"am typing. If it wasn't for TheCherno, we would not be here. And that's", //
			"basically it. Thanks for playing!", //
			" ", //
			"You can check out TheCherno's YouTube channel at", //
			"https://www.youtube.com/user/TheChernoProject", //
			" ", //
			"(Press escape to go back to the main menu)" };
		
		for (int i = 0; i < text.length; i++) {
			render.drawText(text[i], 10 + 2, 80 + i * 30 + 2, 24, 0, 0);
			render.drawText(text[i], 10, 80 + i * 30, 24, 0, 0xffffff);
		}
		render.drawText(options, 15 + 3, 500 + 3, 50, 1, 0);
		render.drawText(options, 15, 500, 50, 1, 0xffffff);
	}

}
