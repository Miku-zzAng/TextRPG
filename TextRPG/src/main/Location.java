package main;

import java.util.*;

// 맵 클래스
public class Location {
	private String name; // 위치의 이름
	private ArrayList<Monster> monsters; // 이 위치에서 만날 수 있는 몬스터 리스트
	private ArrayList<Location> connectedLocations; // 이동 가능한 위치를 저장하는 ArrayList
	private Game game; // Main 클래스에 대한 참조
	private String imageUrl;
	private String bgmUrl;
	private Map<Monster, Double> monsterRandom = new HashMap<>();
	
	// 생성자
	public Location(String name, Game game, String imageUrl, String bgmUrl) {
		this.name = name;
		this.game = game;
		this.monsters = new ArrayList<>();
		this.connectedLocations = new ArrayList<>(); // ArrayList 초기화
		this.imageUrl = imageUrl;
        this.bgmUrl = bgmUrl;
	}

	// 몬스터를 이 위치에 추가하는 메소드
	// 해당 위치에 새로운 몬스터를 추가하는 역할
	// 해당 메소드를 통해 각 위치마다 만날 수 있는 몬스터들을 설정할 수 있음
	public void addMonster(Monster monster, double randoms) {
		monsters.add(monster);
		monsterRandom.put(monster, randoms);
	}

	// imageUrl에 접근하기 위한 getter 메소드
	public String getImageUrl() {
		return this.imageUrl;
	}

	public String getBgmUrl() {
		return this.bgmUrl;
	}

	// 이동 가능한 위치를 추가하는 메소드
	public void addConnectedLocation(Location location) {
		this.connectedLocations.add(location);
	}

	public ArrayList<Location> getConnectedLocation() {
		return this.connectedLocations;
	}

	// 이 위치의 이름을 반환하는 메소드
	public String getName() {
		return this.name;
	}

	// 이동 가능한 위치를 표시하는 메소드
	public void showConnectedLocations() {
		game.updateCommandInfoArea("<b>어디로 이동하시겠습니까?</b><br><br>");
		for (int i = 0; i < connectedLocations.size(); i++) {
			game.updateCommandInfoArea((i + 1) + ". " + connectedLocations.get(i).getName());
		}
		game.updateCommandInfoArea("0. 돌아가기<br>───────────────────────────");
	}

	@Override
	public String toString() {
		return String.format("<font color='black'>현재 위치: </font><font color='rgb(255,0,255)'>%s<br></font>", this.name); // 400px;'>
	}

	// 플레이어가 '탐색'을 선택했을 때 호출되는 메소드
	public Monster explore() {
		if (monsters.isEmpty()) {
			return null;
		} else {
			Random rand = new Random();
			double chance = rand.nextDouble();
			
			// 확률에 따른 몬스터 선택
			for (int i = 0; i < monsters.size(); i++) {
				if (chance <= monsterRandom.get(monsters.get(i))) {
					Monster selectedMonster = monsters.get(i);
					Monster newMonster = new Monster(selectedMonster.getName(), selectedMonster.getMaxHp(),
							selectedMonster.getAttackPower(), selectedMonster.getGame(), selectedMonster.getExp(),
							selectedMonster.getLevel(), selectedMonster.getImageUrl(), selectedMonster.getDeathSoundPath());

					// LootTable도 복사
					newMonster.setLootTable(new LootTable(selectedMonster.getLootTable()));

					return newMonster;
				}
				chance -= monsterRandom.get(monsters.get(i));
			}
			return null; // 만약 모든 확률을 넘어서면 아무것도 찾지 못한 것으로 간주
		}
	}
}