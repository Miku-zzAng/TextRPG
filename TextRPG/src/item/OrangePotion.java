package item;

import java.util.*;
import main.*;

public class OrangePotion extends ConsumableItem {
	public OrangePotion() {
		super("주황 포션", "Consumable", "붉은 약초의 농축 물약이다. HP를 약 150 회복시킨다.");
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
	    player.heal(150);
	    playUseSound("wav/potion.wav");
	}
}
