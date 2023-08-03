package monster;

import main.*;
import item.*;

public class 초록버섯 extends Monster {
	private static final int ATTACK_POWER = 82;
	
	private static final int EXP = 26;
	
	private static final int HP = 250;
	
	private static final int LEVEL = 15;

	private static final String NAME = "초록 버섯";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters013.gif"; 
			
	private static final String DEATH_SOUND_PATH = "wav/diewav/omdie.wav";
	
		public 초록버섯(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(whitePotion, 50);
        addLootItem(redPotion, 70);
        addLootItem(bluePotion, 50);
        addLootItem(orangePotion, 60);
	}
	
}

