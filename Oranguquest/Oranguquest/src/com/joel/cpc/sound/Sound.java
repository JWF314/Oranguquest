package com.joel.cpc.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	private AudioClip sound;
	
	private Sound(String name) {
		sound = Applet.newAudioClip(Sound.class.getResource(name));
	}
	
	public void play(boolean loop) {
		if (loop) {
			sound.loop();
		} else {
			sound.play();
		}
	}
	
	public void stop() {
		sound.stop();
	}
	
	public static void stopAll() {
		
	}

}
