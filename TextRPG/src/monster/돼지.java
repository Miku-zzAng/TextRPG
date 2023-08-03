package monster;

import main.*;
import item.*;

public class 돼지 extends Monster {
	private static final int ATTACK_POWER = 52;
	
	private static final int EXP = 15;
	
	private static final int HP = 75;
	
	private static final int LEVEL = 7;

	private static final String NAME = "돼지";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters007.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/pigdie.wav";
	
		public 돼지(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(redPotion, 70);
        addLootItem(orangePotion, 50);
        addLootItem(whitePotion, 40);
        addLootItem(bluePotion, 50);
	}
	
}