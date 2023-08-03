package monster;

import main.*;
import item.*;

public class 슬라임 extends Monster {
	private static final int ATTACK_POWER = 42;
	
	private static final int EXP = 10;
	
	private static final int HP = 50;
	
	private static final int LEVEL = 6;

	private static final String NAME = "슬라임";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters006.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/sldie.wav";
	
		public 슬라임(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(redPotion, 70);
        addLootItem(orangePotion, 40);
        addLootItem(whitePotion, 30);
        addLootItem(bluePotion, 50);
	}
	
}