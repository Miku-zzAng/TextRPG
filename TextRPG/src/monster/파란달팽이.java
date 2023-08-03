package monster;

import main.*;
import item.*;

public class 파란달팽이 extends Monster {
	private static final int ATTACK_POWER = 27;
	
	private static final int EXP = 4;
	
	private static final int HP = 15;
	
	private static final int LEVEL = 2;

	private static final String NAME = "파란 달팽이";
	
	private static final String IMAGE_URL = "https://bbb.hidden-street.net/sites/bbb.hidden-street.net/files/imagecache/monster/sites/global.hidden-street.net/files/monsters/monsters002_0.gif";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/rsdie.wav";
	
		public 파란달팽이(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(redPotion, 40);
        addLootItem(orangePotion, 20);
        addLootItem(whitePotion, 10);
	}
	
}