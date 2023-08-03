package monster;

import main.*;
import item.*;

public class 주황버섯 extends Monster {
	private static final int ATTACK_POWER = 90;
	
	private static final int EXP = 32;
	
	private static final int HP = 350;
	
	private static final int LEVEL = 20;

	private static final String NAME = "파란 버섯";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters015.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/omdie.wav";
	
		public 주황버섯(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(whitePotion, 60);
        addLootItem(redPotion, 30);
        addLootItem(bluePotion, 80);
        addLootItem(orangePotion, 30);
	}
	
}

