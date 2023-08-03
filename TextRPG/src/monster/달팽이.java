package monster;

import main.*;
import item.*;

public class 달팽이 extends Monster {
	private static final int ATTACK_POWER = 22;
	
	private static final int EXP = 3;
	
	private static final int HP = 8;
	
	private static final int LEVEL = 1;

	private static final String NAME = "달팽이";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters001.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/rsdie.wav";
	
		public 달팽이(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(redPotion, 30);
        addLootItem(orangePotion, 10);
	}
	
}

