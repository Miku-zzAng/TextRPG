package main;

import java.util.*;

public class LootTable {
	private final ArrayList<LootItem> lootItems;

	public LootTable() {
		lootItems = new ArrayList<>();
	}

	public void addLootItem(ConsumableItem item, int dropChance) {
		lootItems.add(new LootItem(item, dropChance));
		// 추가한 아이템의 정보를 출력
	}

	// LootTable 클래스에 복사 생성자 추가
	public LootTable(LootTable other) {
		this.lootItems = new ArrayList<>(other.lootItems);
	}

	public ConsumableItem rollForLoot() {
		Random random = new Random();
		// 각 아이템에 대해 독립적으로 확률을 체크하고, 조건을 만족하는 경우 해당 아이템을 선택

		for (LootItem lootItem : lootItems) {
			if (random.nextInt(100) < lootItem.getDropChance()) {
				return lootItem.getItem(); // 확률을 만족하는 아이템을 즉시 반환하고 loop를 빠져나옴
			}
		}
		return null; // 아무 아이템도 선택되지 않은 경우 null을 반환
	}
}

class LootItem {
	private final ConsumableItem item;
	private final int dropChance;

	public LootItem(ConsumableItem item, int dropChance) {
		this.item = item;
		this.dropChance = dropChance;
	}

	public ConsumableItem getItem() {
		return item;
	}

	public int getDropChance() {
		return dropChance;
	}
}