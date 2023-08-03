package main;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class ConsumableItem extends Item {

	public ConsumableItem(String name, String type, String description) {
		super(name, "Consumable", description);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Item item = (Item) o;
		return Objects.equals(name, item.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public void applyEffect(Player player) {
	}
	
    public void playUseSound(String soundPath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundPath).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}