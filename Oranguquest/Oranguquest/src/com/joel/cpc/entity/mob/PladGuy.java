package com.joel.cpc.entity.mob;

import com.joel.cpc.gfx.Render;
import com.joel.cpc.gfx.Sprite;

public class PladGuy extends Mob {
	
	int xa, ya;
	
	public PladGuy() {
		sprite = Sprite.plad_guy_back;
		x = 255;
		y = 230;
	}
	
	public void tick() {
		if (rand.nextInt(40) == 0) {
			xa = rand.nextInt(3) - 1;
			ya = rand.nextInt(3) - 1;
		}
		
		if (xa != 0 || ya != 0) {
			walking = true;
			move(xa, ya);
		} else {
			walking = false;
		}
	}
	
	public void render(Render render) {
		if (dir == 0) sprite = Sprite.plad_guy_forward;
		if (dir == 1) sprite = Sprite.plad_guy_right;
		if (dir == 2) sprite = Sprite.plad_guy_back;
		if (dir == 3) sprite = Sprite.plad_guy_left;
		
		render.drawMob(x - 16, y - 16, this);
	}

}
