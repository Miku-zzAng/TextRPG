package monster;

import main.*;
import item.*;

public class 스텀프 extends Monster {
	private static final int ATTACK_POWER = 42;
	
	private static final int EXP = 10;
	
	private static final int HP = 50;
	
	private static final int LEVEL = 6;

	private static final String NAME = "슬라임";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters006.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/stdie.wav";
	
		public 스텀프(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(redPotion, 60);
        addLootItem(orangePotion, 30);
        addLootItem(whitePotion, 20);
        addLootItem(bluePotion, 40);
	}
	
}