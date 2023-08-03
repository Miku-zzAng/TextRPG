package monster;

import main.*;
import item.*;

public class 리본돼지 extends Monster {
	private static final int ATTACK_POWER = 70;
	
	private static final int EXP = 20;
	
	private static final int HP = 120;
	
	private static final int LEVEL = 10;

	private static final String NAME = "리본 돼지";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters009.gif";

	private static final String DEATH_SOUND_PATH = "wav/diewav/pigdie.wav";
	
		public 리본돼지(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(whitePotion, 60);
        addLootItem(redPotion, 50);
        addLootItem(bluePotion, 40);
        addLootItem(orangePotion, 70);
	}
	
}

