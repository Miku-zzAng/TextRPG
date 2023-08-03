package item;

import java.util.*;
import main.*;

public class RedPotion extends ConsumableItem {
	public RedPotion() {
		super("빨간 포션", "Consumable", "붉은 약초로 만든 물약이다. HP를 약 50 회복시킨다.");
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
	
	public void applyEffect(Player player) {
	    player.heal(50);
	    playUseSound("wav/potion.wav");
	}
}
