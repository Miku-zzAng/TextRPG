package item;

import java.util.*;
import main.*;

public class BluePotion extends ConsumableItem {
	public BluePotion() {
		super("파란 포션", "Consumable", "푸른 약초로 만든 물약이다. MP를 약 100 회복시킨다.");
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
	    player.mana(100); 
	    playUseSound("wav/potion.wav");
	}
}
