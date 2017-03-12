package com.joel.cpc.menu;

import com.joel.cpc.Game;
import com.joel.cpc.gfx.Render;
import com.joel.cpc.input.KeyInput;
import com.joel.cpc.sound.Sound;

public class AboutMenu extends Menu {
	
	public AboutMenu(KeyInput input) {
		super(input);
	}
	
	int timer = 18;
	String options = "> Next <";
	int selected = 0;
	
	public void tick() {
		if (timer > 0) timer--;
		
		if (selected == 0 && input.use && timer == 0) {
			Game.menu = new AboutMenu2(input);
		}
		if (input.back) Game.menu = new TitleMenu(input);
	}
	
	public void render(Render render) {
		String[] text = { "Ok, if you're here, its obvious that you want to", //
			"know how this game started. It started with TheChernoProject. Since", //
			"like 2016 I thought it would be very cool to learn how to code games.", //
			"So I searched the Internet on how to program games. And I came across", //
			"a cool guy named TheChernoProject (Obviously, this is not his real name", //
			"but whatever) and he showed how to make cool games from scratch in", //
			"Java. So, I learned from him. I used some of his source code from his", //
			"game 'Genesis' because the code from his video series is the basically", //
			"the exact same. So this is why I give credit to TheChernoProject for", //
			"teaching me how to make games in Java.", //
			" ", //
			"(This is our first game, we are just dipping our toes in the water)", //
			"(Press escape to go back to the main menu)" };
		
		for (int i = 0; i < text.length; i++) {
			render.drawText(text[i], 10 + 2, 80 + i * 30 + 2, 24, 0, 0);
			render.drawText(text[i], 10, 80 + i * 30, 24, 0, 0xffffff);
		}
		render.drawText(options, 15 + 3, 500 + 3, 50, 1, 0);
		render.drawText(options, 15, 500, 50, 1, 0xffffff);
	}

}
