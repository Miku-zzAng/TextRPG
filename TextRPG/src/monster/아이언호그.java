
package monster;

import main.*;
import item.*;

public class 아이언호그 extends Monster {
	private static final int ATTACK_POWER = 95;
	
	private static final int EXP = 99;
	
	private static final int HP = 2200;
	
	private static final int LEVEL = 42;

	private static final String NAME = "아이언 호그";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters036.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/pigdie.wav";
	
		public 아이언호그(Game game) {
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

