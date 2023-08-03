package monster;

import main.*;
import item.*;

public class 스포아 extends Monster {
	private static final int ATTACK_POWER = 32;
	
	private static final int EXP = 5;
	
	private static final int HP = 20;
	
	private static final int LEVEL = 2;

	private static final String NAME = "스포아";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters003.gif";
			
	private static final String DEATH_SOUND_PATH = "wav/diewav/omdie.wav";
	
		public 스포아(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(redPotion, 30);
        addLootItem(orangePotion, 10);
	}
	
}