
package monster;

import main.*;
import item.*;

public class 다크스텀프 extends Monster {
	private static final int ATTACK_POWER = 65;
	
	private static final int EXP = 18;
	
	private static final int HP = 250;
	
	private static final int LEVEL = 10;

	private static final String NAME = "다크 스텀프";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters010.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/stdie.wav";
	
		public 다크스텀프(Game game) {
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

