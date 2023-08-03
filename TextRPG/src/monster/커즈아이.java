package monster;

import main.*;
import item.*;

public class 커즈아이 extends Monster {
	private static final int ATTACK_POWER = 120;
	
	private static final int EXP = 70;

	private static final int HP = 1250;
	
	private static final int LEVEL = 35;

	private static final String NAME = "커즈아이";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters026.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/omdie.wav";
	
		public 커즈아이(Game game) {
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

