package monster;

import main.*;
import item.*;

public class 빨간달팽이 extends Monster {
	private static final int ATTACK_POWER = 35;
	
	private static final int EXP = 8;
	
	private static final int HP = 45;
	
	private static final int LEVEL = 4;

	private static final String NAME = "빨간 달팽이";
	
	private static final String IMAGE_URL = "https://static.wikia.nocookie.net/maplestory/images/9/93/%EB%B9%A8%EA%B0%84_%EB%8B%AC%ED%8C%BD%EC%9D%B4.png/revision/latest?cb=20220703125917&path-prefix=ko";
	
	private static final String DEATH_SOUND_PATH = "wav/diewav/rsdie.wav";
	
		public 빨간달팽이(Game game) {
		super(NAME, HP, ATTACK_POWER, game, EXP, LEVEL, IMAGE_URL, DEATH_SOUND_PATH);
		
		ConsumableItem whitePotion = new WhitePotion();
        ConsumableItem redPotion = new RedPotion();
        ConsumableItem bluePotion = new BluePotion();
        ConsumableItem orangePotion = new OrangePotion();
        addLootItem(whitePotion, 20);
        addLootItem(redPotion, 50);
        addLootItem(bluePotion, 30);
        addLootItem(orangePotion, 30);
	}
	
}

