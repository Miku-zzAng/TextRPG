package monster;

import main.*;
import item.*;

public class 파란버섯 extends Monster {
	private static final int ATTACK_POWER = 52;
	
	private static final int EXP = 18;
	
	private static final int HP = 80;
	
	private static final int LEVEL = 8;

	private static final String NAME = "주황 버섯";
	
	private static final String IMAGE_URL = "https://static.wikia.nocookie.net/maplestory/images/e/e9/%EC%A3%BC%ED%99%A9%EB%B2%84%EC%84%AF.png/revision/latest?cb=20220702055839&path-prefix=ko";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/omdie.wav";
	
		public 파란버섯(Game game) {
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

