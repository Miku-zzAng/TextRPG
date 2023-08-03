package main;

import java.util.*;

public class Inventory {
	private Map<Item, Integer> allItems; // 모든 아이템을 저장하는 맵
	private Map<ConsumableItem, Integer> consumableItems; // 소비 아이템을 저장하는 맵
	// private Map<EquipmentItem, Integer> equipmentItems; // 장비 아이템을 저장하는 맵
	// private Map<MaterialItem, Integer> materialItems; // 재료 아이템을 저장하는 맵

	public Inventory() {
		allItems = new HashMap<>();
		consumableItems = new HashMap<>();
		// equipmentItems = new HashMap<>();
		// materialItems = new HashMap<>();
	}

	// 아이템 추가
	public void addItem(Item item, int quantity) {
		if (item instanceof ConsumableItem) {
			consumableItems.put((ConsumableItem) item,
					consumableItems.getOrDefault((ConsumableItem) item, 0) + quantity);
		} else {
			allItems.put(item, allItems.getOrDefault(item, 0) + quantity);
		}
	}

	// 아이템 제거
	public boolean removeItem(Item item, int quantity) {
		if (item instanceof ConsumableItem) {
			if (!consumableItems.containsKey(item) || consumableItems.get((ConsumableItem) item) < quantity) {
				return false;
			}
			consumableItems.put((ConsumableItem) item, consumableItems.get((ConsumableItem) item) - quantity);
			if (consumableItems.get((ConsumableItem) item) == 0) {
				consumableItems.remove((ConsumableItem) item);
			}
		} else {
			if (!allItems.containsKey(item) || allItems.get(item) < quantity) {
				return false;
			}
			allItems.put(item, allItems.get(item) - quantity);
			if (allItems.get(item) == 0) {
				allItems.remove(item);
			}
		}
		return true;
	}

	// 소비 아이템이 비어 있는지 확인하는 메소드
	public boolean isConsumableInventoryEmpty() {
		return consumableItems.isEmpty();
	}

	// 소비 아이템 맵을 반환하는 메소드
	public Map<ConsumableItem, Integer> getConsumableItems() {
		return consumableItems;
	}

	// 인벤토리 상태 표시
	public void showInventory() {
		// 원하는 아이템 유형의 인벤토리를 보여줄 수 있음
	}

	public void showConsumableInventory() {
		if (consumableItems.isEmpty()) {
			System.out.println("소비 아이템 인벤토리가 비어있습니다.");
			return;
		}

		int index = 1;
		for (ConsumableItem item : consumableItems.keySet()) {
			System.out.println(index + ". " + item.getName() + " - 수량: " + consumableItems.get(item) + "개");
			index++;
		}
		System.out.println("0. 돌아가기");
	}

	// 소비 아이템을 제거하는 메소드
	public boolean removeConsumableItem(ConsumableItem item, int quantity) {
		if (!consumableItems.containsKey(item) || consumableItems.get(item) < quantity) {
			return false;
		}
		consumableItems.put(item, consumableItems.get(item) - quantity);
		if (consumableItems.get(item) == 0) {
			consumableItems.remove(item);
		}
		return removeItem(item, quantity);
	}
}
